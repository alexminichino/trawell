package com.trawell.controllers;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpSession;

import com.trawell.models.Post;
import com.trawell.models.User;
import com.trawell.services.ComplaintService;
import com.trawell.services.PostService;
import com.trawell.utilities.email.EmailSenderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.ozimov.springboot.mail.configuration.EnableEmailTools;

/**
 * @author Umberto Russomando In questo controller andranno mappate tutte le
 *         funzionalità relative al post per la comunicazione REST
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
     * This method allows an user to delete a post
     * 
     * @author Umberto Russomando
     * @param session
     * @param id
     * @return an HttpStatus
     */
    @PostMapping(value = "/post/delete/{id}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> deletePost(HttpSession session, @PathVariable("id") Long id) {
        User user = (User) session.getAttribute("user");

        User postOwner = dao.findOne(id).getUser();

        if (user != null) {
            if (user.getId().equals(postOwner.getId())) {
                dao.delete(id);

                return new ResponseEntity<>(HttpStatus.OK);
            }

            if (user.getIsAdmin()) {

                String text = "Your post has been removed by an admin,contact us for more info.";
                String object = "Reported Post";
                String emailTo = postOwner.getMail();
                String name = postOwner.getName();

                dao.delete(id);

                try {
                    emailService.sendEmail(text, object, emailTo, name);
                } catch (UnsupportedEncodingException e) {
                    System.out.println(e + "Errore");
                }

                return new ResponseEntity<>(HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    /**
     * This method allows an user to report a post
     * 
     * @author Umberto Russomando
     * @param session
     * @param id
     * @return an HttpStatus
     */
    @PostMapping(value = "/post/report/{id}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> reportPost(HttpSession session, @PathVariable("id") Long id) {

        User user = (User) session.getAttribute("user");

        if (user != null) {

            Post post = dao.findOne(id);
            post.setIsReported(true);
            dao.update(post);

            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * This method allows an user to report a post
     * 
     * @author Umberto Russomando
     * @param session
     * @param id
     * @return an HttpStatus
     */
    @PostMapping(value = "/post/discard/{id}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> discardPost(HttpSession session, @PathVariable("id") Long id) {

        User user = (User) session.getAttribute("user");

        if (user != null) {

            Post post = dao.findOne(id);
            post.setIsReported(false);
            dao.update(post);

            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
