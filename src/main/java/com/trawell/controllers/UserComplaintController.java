package com.trawell.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.trawell.models.Complaint;
import com.trawell.models.User;
import com.trawell.services.ComplaintService;
import com.trawell.services.IComplaintService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("Complaint")
public class UserComplaintController {

    @Autowired
   ComplaintService dao;
// GET [method = RequestMethod.GET] is a default method for any request. 
// So we do not need to mention explicitly
    
    @GetMapping("/testComplaint")
    public ModelAndView landing() {
        return new ModelAndView("pages/complaint/userComplaint", "command", new Complaint());
    }

    @PostMapping("/addComplaint")
    public String onSubmit(@ModelAttribute Complaint complaint , HttpSession session) {
        
        User user = (User) session.getAttribute("user");
        complaint.setIdUser(user.getId());
        dao.create(complaint);       
        return "pages/complaint/userComplaint";
       
    }
}