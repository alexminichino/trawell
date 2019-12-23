package com.trawell.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.trawell.models.Complaint;
import com.trawell.services.IComplaintService;

import org.springframework.stereotype.Controller;


import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserComplaintController {

    IComplaintService iComplaintService;
// GET [method = RequestMethod.GET] is a default method for any request. 
// So we do not need to mention explicitly
    
    @PostMapping(value = "/addComplaint")
    public String submit(@Valid @ModelAttribute Complaint complaint, HttpSession session) {
        complaint.setIdUser((int) session.getAttribute("id"));
        iComplaintService.create(complaint);        
        return "pages/home/index";
    }
}