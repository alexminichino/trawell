package com.trawell;

import static org.junit.Assert.assertEquals;

import com.trawell.controllers.ComplaintController;
import com.trawell.models.Complaint;
import com.trawell.models.User;
import com.trawell.services.ComplaintService;
import com.mysql.cj.Session;
import com.trawell.controllers.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Date;

import javax.servlet.http.HttpSession;


@RunWith(SpringRunner.class)
@WebMvcTest(ComplaintController.class)
public class ComplaintControllerTest {

    
    HttpSession session;

    
    Complaint complaint = new Complaint();


    @Mock
    User instance = new User();

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void before() {
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

        this.mockMvc = standaloneSetup(new ComplaintController().build());
    }

    @After
    public void after() {
        System.out.println("After");
    }


    
 
    @MockBean
    ComplaintService daoMock;

    @Test
    public void viewUserComplaint() {

        ComplaintController instance = new ComplaintController();
        assertEquals("pages/complaint/userComplaint", instance.viewUserComplaint());
    }

    /*
     @Test
    public void TestSendComplaint()  throws Exception{
      
        assertEquals("pages/complaint/userComplaint", instance.sendComplaint(complaint, session));
        
    }
    */
   

}