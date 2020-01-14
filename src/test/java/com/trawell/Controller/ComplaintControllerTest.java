package com.trawell.Controller;

import static org.junit.Assert.assertEquals;

import com.trawell.models.*;
import com.trawell.services.*;
import com.trawell.controllers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import org.springframework.mock.web.MockHttpSession;

import org.springframework.ui.Model;

import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;


/**
 * @author Paolo Fasano
 */
@RunWith(MockitoJUnitRunner.class)
public class ComplaintControllerTest {

   
    @Mock
    ComplaintService dao = new ComplaintService();

    ArrayList<Complaint> complaints = new ArrayList<Complaint>();
    Complaint complaint = new Complaint();

    @InjectMocks
    ComplaintController controller = new ComplaintController();

    @Mock
    Model model;

    User instance = new User();

    MockHttpSession session = new MockHttpSession();

    @Before
    public void setup() {

        System.out.println("Before");
        instance.setName("Name");
        instance.setBirth(new Date(0, 0, 0));
        instance.setBanned(false);
        instance.setPhone("Phone");
        instance.setIsAdmin(false);
        instance.setIsBanned(false);
        instance.setMail("Mail");
        instance.setSurname("Surname");
        instance.setUsername("Username");
        instance.setPassword("Password");

        session.setAttribute("user", instance);

       complaint = new Complaint(1L, 0L, "complaintObject", "complaintDescription", "complaintMail", 0L, true, "complaintAnswere");
       complaints.add(complaint);
    }

    @After
    public void after() {
        System.out.println("After");
    }

    @Test
    public void TestViewUserComplaint() {
        ComplaintController controller = new ComplaintController();
        
        assertEquals("pages/complaint/userComplaint", controller.viewUserComplaint());
    }

    @Test
    public void TestPrevComplaint() throws Exception {
        session.setAttribute("complaintPos", 1);
        session.setAttribute("Complaints", complaints);
        //ComplaintController controller = new ComplaintController();
        System.out.println("a");
        System.out.println(session.getAttribute("user").toString());
        assertEquals("pages/complaint/adminComplaint", controller.prevComplaint(session,model));
    }

    @Test
    public void TestNextComplaint() throws Exception {
        session.setAttribute("complaintPos", 1);
        session.setAttribute("Complaints", complaints); 
        assertEquals("pages/complaint/adminComplaint", controller.nextComplaint(session,model));
    }

    @Test
    public void TestSendComplaint() throws Exception {
        System.out.println("a");
        System.out.println(complaint.toString()+" "+session.getAttribute("user").toString());
        instance.setId(1L);
        session.setAttribute("user", instance);
        Complaint complainte = new Complaint(null, 0L, "complaintObject", "complaintDescription", "complaintMail", 0L, true, "complaintAnswere");     
        assertEquals("pages/complaint/userComplaint", controller.sendComplaint(complaint, session));
    }

    @Test
    public void TestViewComplaint() throws Exception {
       // ComplaintController controller = new ComplaintController();
        System.out.println("a");
        System.out.println(session.getAttribute("user").toString());
        assertEquals("pages/complaint/userComplaint", controller.viewComplaint(session,model));
    }

    @Test
    public void TestViewComplaintNo() throws Exception {
        //ComplaintController controller = new ComplaintController();
        System.out.println("a");
        instance.setIsAdmin(true);
        session.setAttribute("user", instance);
        System.out.println(session.getAttribute("user").toString());

        when(dao.findAll()).thenReturn(complaints);
        assertEquals("pages/complaint/noComplaint", controller.viewComplaint(session,model));
    }

    @Test
    public void TestViewComplaintAdmin() throws Exception {
        //ComplaintController controller = new ComplaintController();
        System.out.println("a");
        instance.setIsAdmin(true);
        session.setAttribute("user", instance);

        
        System.out.println(session.getAttribute("user").toString());
        Complaint complainte = new Complaint(null, 0L, "complaintObject", "complaintDescription", "complaintMail", 0L, false, "complaintAnswere");     
        complaints.add(complainte);
        when(dao.findAll()).thenReturn(complaints);
        assertEquals("pages/complaint/adminComplaint", controller.viewComplaint(session,model));
    }

    /*
    @Test
    public void TestAnswereComplaint() throws Exception {
        //ComplaintController controller = new ComplaintController();
        System.out.println("a");
        instance.setIsAdmin(true);
        session.setAttribute("user", instance);
        session.setAttribute("complaintPos", 0);
        session.setAttribute("Complaints", complaints);
        
        System.out.println(session.getAttribute("user").toString());
        Complaint complainte = new Complaint(1L, 0L, "complaintObject", "complaintDescription", "super-pablo@hotmail.it", 0L, false, "a");     
        complaints.add(complainte);
        session.setAttribute("complaintPos", 1);
        session.setAttribute("Complaints", complaints);

        when(dao.update(complainte)).thenReturn(complaint);
        assertEquals("pages/complaint/adminComplaint", controller.answereComplaint(complaint, model, session));
    }

    */
    
}