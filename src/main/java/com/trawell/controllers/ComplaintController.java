package com.trawell.controllers;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.trawell.models.Complaint;
import com.trawell.models.User;
import com.trawell.services.ComplaintService;
import com.trawell.utilities.email.EmailSenderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.ozimov.springboot.mail.configuration.EnableEmailTools;

@EnableEmailTools
@Controller
@RequestMapping("Complaint")
public class ComplaintController {

    @Autowired
    ComplaintService dao;
    @Autowired
    private EmailSenderService emailService;

    String homeUserC = "pages/complaint/userComplaint";
    String homeAdminC = "pages/complaint/adminComplaint";

    /**
     * This method allows a user (non-admin) to view the page to create a complaint
     * and to send it to the admins
     * 
     * @author Paolo Fasano
     */
    @GetMapping("/userViewComplaint")
    public String viewUserComplaint() {
        return homeUserC;
    }

    /**
     * This method allows a user (non-admin) to create a complaint and to send it to
     * the admins
     * 
     * @author Paolo Fasano
     * @param user
     * @param complaint
     */
    @PostMapping("/addComplaint")
    public String sendComplaint(@ModelAttribute Complaint complaint, HttpSession session) {

        User user = (User) session.getAttribute("user");
        complaint.setIdUser(user.getId());
        dao.create(complaint);
        return homeUserC;

    }

    /**
     * if the user is not an admin it allows to to view the page to create a
     * complaint and to send it to the admins else if is an admin This method allows
     * to view a complaint and answere it
     * 
     * @author Paolo Fasano
     * @param complaints
     * @param allComplaint
     * @param c
     * @param n
     * @param i
     */

    @GetMapping("/viewComplaint")
    public String viewComplaint(HttpSession session, Model model) {

        User user = (User) session.getAttribute("user");
        if (!(user.getIsAdmin()))
            return homeUserC;

        int i = 0;
        ArrayList<Complaint> allComplaints = (ArrayList<Complaint>) dao.findAll();
        ArrayList<Complaint> complaints = new ArrayList<>();

        int n = 0;
        for (int x = 0; x < allComplaints.size(); x++) {
            Complaint c = allComplaints.get(x);
            if (!(c.getComplaintAnswered())) {
                complaints.add(n, allComplaints.get(x));
                n++;
            }
        }

        if (complaints.isEmpty()) {
            return "pages/complaint/noComplaint";
        }

        model.addAttribute(complaints.get(i));
        session.setAttribute("complaintPos", i);
        session.setAttribute("Complaints", complaints);

        return homeAdminC;
    }

    /**
     * This method allows a admin to view the next complaint
     * 
     * @author Paolo Fasano
     * @param complaints
     * @param l
     * @param i
     */
    @GetMapping("/prevComplaint")
    public String prevComplaint(HttpSession session, Model model) {
        int i = (int) session.getAttribute("complaintPos");
        ArrayList<Complaint> complaints = (ArrayList<Complaint>) session.getAttribute("Complaints");
        int l = complaints.size();

        i--;
        if (i < 0) {
            i = l - 1;
        }

        model.addAttribute(complaints.get(i));
        session.setAttribute("complaintPos", i);
        session.setAttribute("Complaints", complaints);
        return homeAdminC;

    }

    /**
     * This method allows a admin to view the previeus complaint
     * 
     * @author Paolo Fasano
     * @param complaints
     * @param l
     * @param i
     */
    @GetMapping("/nextComplaint")
    public String nextComplaint(HttpSession session, Model model) {
        int i = (int) session.getAttribute("complaintPos");
        ArrayList<Complaint> complaints = (ArrayList<Complaint>) session.getAttribute("Complaints");
        int l = complaints.size();

        i++;
        if (i > l - 1) {
            i = 0;
        }

        model.addAttribute(complaints.get(i));
        session.setAttribute("complaintPos", i);
        session.setAttribute("Complaints", complaints);
        return homeAdminC;
    }

    /**
     * This method allows a admin to answere the complaint
     * 
     * @author Paolo Fasano
     * @param complaints
     * @param c
     * @param i
     */

    @PostMapping("/answereComplaint")
    public String answereComplaint(@ModelAttribute Complaint complaint, Model model, HttpSession session)
            throws UnsupportedEncodingException{

        int i = (int) session.getAttribute("complaintPos");
        ArrayList<Complaint> complaints = (ArrayList<Complaint>) session.getAttribute("Complaints");
        long id = 0;

        Complaint c = complaints.get(i);
        User user = (User) session.getAttribute("user");

        if (user.getId() == null)
            id = -1;
        else
            id = user.getId();

        c.setIdAnswerer(id);
        c.setComplaintAnswere(complaint.getComplaintAnswere());
        c.setComplaintAnswered(true);
        dao.update(c);

        String text = "The following mail is the answere to: /n" + c.getComplaintDescription() + "/n"
                + c.getComplaintAnswere()
                + "/n For more information contact us on Trawell@hotmail.it /n  Thanks for your complaint the TraWell team";

        try {
            emailService.sendEmail(text, ("Answere to: " + c.getComplaintObject()), c.getMail(), "TraWell");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        int l = complaints.size();
        i++;
        if (i > l - 1) {
            i = 0;
        }
        model.addAttribute(complaints.get(i));
        session.setAttribute("complaintPos", i);
        session.setAttribute("Complaints", complaints);

        return homeAdminC;
    }

}