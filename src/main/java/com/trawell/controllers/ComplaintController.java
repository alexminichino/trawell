package com.trawell.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.google.gson.Gson;
import com.trawell.models.Complaint;
import com.trawell.models.User;
import com.trawell.services.ComplaintService;
import com.trawell.services.IComplaintService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping("Complaint")
public class ComplaintController {

    @Autowired
   ComplaintService dao;
// GET [method = RequestMethod.GET] is a default method for any request. 
// So we do not need to mention explicitly
    
    @GetMapping("/testComplaint")
    public ModelAndView landing() {
        return new ModelAndView("pages/complaint/adminComplaint", "command", new Complaint());
    }

    @PostMapping("/addComplaint")
    public String sendComplaint(@ModelAttribute Complaint complaint , HttpSession session) {
        
        User user = (User) session.getAttribute("user");
        complaint.setIdUser(user.getId());
        dao.create(complaint);       
        return "pages/complaint/userComplaint";
       
    }

    @GetMapping("/viewComplaint")
    public String viewComplaint(HttpSession session, Model model)
    {
        int i = 0;
        ArrayList<Complaint> allComplaints = (ArrayList<Complaint>) dao.findAll();
        ArrayList<Complaint> complaints = new ArrayList<Complaint>();
        
        int n=0;
        for(int x = 0; x < allComplaints.size(); x++)
        {
            Complaint c = allComplaints.get(x);
            if(!(c.getComplaintAnswered()))
            {
                complaints.add(n, allComplaints.get(x));
                n++; 
            }
        }
                         
        model.addAttribute(complaints.get(i));
        session.setAttribute("complaintPos", i);
        session.setAttribute("Complaints", complaints);

        return "pages/complaint/adminComplaint";
    }

    @GetMapping("/prevComplaint")
    public String prevComplaint(HttpSession session, Model model)
    {
        int i = (int) session.getAttribute("complaintPos");
        ArrayList<Complaint> complaints = (ArrayList<Complaint>) session.getAttribute("Complaints");
        int l = complaints.size();

        i--;              
        if(i<0)
        {
         i=l-1;
        }     
        
        model.addAttribute(complaints.get(i));
        session.setAttribute("complaintPos", i);
        session.setAttribute("Complaints", complaints);
        return "pages/complaint/adminComplaint";
        
    }

    @GetMapping("/nextComplaint")
    public String nextComplaint(HttpSession session, Model model)
    {
        int i = (int) session.getAttribute("complaintPos");
        ArrayList<Complaint> complaints = (ArrayList<Complaint>) session.getAttribute("Complaints");
        int l = complaints.size();
        
        i++;        
        if(i>l-1)
        {
            i=0;
        }
        
        model.addAttribute(complaints.get(i));
        session.setAttribute("complaintPos", i);
        session.setAttribute("Complaints", complaints);
        return "pages/complaint/adminComplaint";
    }

    @PostMapping("/answereComplaint")
    public String answereComplaint(@ModelAttribute Complaint complaint, Model model , HttpSession session) 
    {

        int i = (int) session.getAttribute("complaintPos");
        ArrayList<Complaint> complaints = (ArrayList<Complaint>) session.getAttribute("Complaints");

        Complaint c = complaints.get(i);
        //User user = (User) session.getAttribute("user");
        //c.setIdAnswerer(user.getId());
        c.setIdAnswerer((long) 1);
        c.setComplaintAnswere(complaint.getComplaintAnswere());
        c.setComplaintAnswered(true);
        dao.update(c);

        int l = complaints.size();
        i++;        
        if(i>l-1)
        {
            i=0;
        }      
        model.addAttribute(complaints.get(i));
        session.setAttribute("complaintPos", i);
        session.setAttribute("Complaints", complaints);
        return "pages/complaint/adminComplaint";
    }


    
}