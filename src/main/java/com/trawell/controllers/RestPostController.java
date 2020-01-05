package com.trawell.controllers;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpSession;

import com.trawell.models.Complaint;
import com.trawell.models.Post;
import com.trawell.models.User;
import com.trawell.services.ComplaintService;
import com.trawell.services.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.trawell.utilities.email.EmailSenderService;
import it.ozimov.springboot.mail.configuration.EnableEmailTools;

/**
 * @author Umberto Russomando 
 * In questo controller andranno mappate tutte le funzionalità relative al post per la comunicazione REST
 * 
 */

@EnableEmailTools
@RestController
@RequestMapping(value = "/api")
public class RestPostController {

    @Autowired
    private PostService dao;

    @Autowired
    ComplaintService complaintDao;

    @Autowired
    private EmailSenderService emailService;

    /**
     * @author Umberto Russomando
     * @param session
     * @param id
     * @return an HttpStatus
     */
    @RequestMapping(value = "/post/delete/{id}", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> deletePost(HttpSession session, @PathVariable("id") Long id) {
        User user = (User) session.getAttribute("user");

        if (user != null) {
            dao.delete(id);
            return new ResponseEntity<Post>(HttpStatus.OK);
        }

        return new ResponseEntity<Post>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * @author Umberto Russomando
     * @param session
     * @param id
     * @return an HttpStatus
     */
    @RequestMapping(value = "/post/report/{id}", method = RequestMethod.POST,  consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> reportPost(HttpSession session, @PathVariable("id") Long id) {

        User user = (User) session.getAttribute("user");

        if (user != null) {
            // crea il complaint
            Complaint reportComplaint = new Complaint();
            reportComplaint.setId(user.getId());
            reportComplaint.setMail(user.getMail());
            reportComplaint.setComplaintObject("report Post");
            reportComplaint.setComplaintDescription("The user wants to repot a post");

            // salva il complaint nel db
            complaintDao.create(reportComplaint);

            // crea ed invia la mail
            String text = "The user: " + user.getName() + " " + user.getSurname()
                    + " has reported the post with the following id: " + id;
            String name = user.getName() + " " + user.getSurname();

            try {
                emailService.sendReportEmail(text, "report Post", user.getMail(), name);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }


            return new ResponseEntity<Post>(HttpStatus.OK);
        }
        
        return new ResponseEntity<Post>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
