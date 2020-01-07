package com.trawell.utilities.email;

import org.springframework.stereotype.Service;

import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;


import static com.google.common.collect.Lists.newArrayList;


/**
 * @author Umberto Russomando, Paolo Fasano
 * This service is the one responsible for creating and sending the email to the admin
 */

@Service
public class EmailSenderService {

    @Autowired
    private EmailService emailService;

/**
 * This method create and send the email to the admin
 * @author Umberto Russomando 
 * @param serialNumber
 * @param emailTo
 * @param name
 * @throws UnsupportedEncodingException
 */
    public void sendSerialNumberByEmail(String serialNumber, String emailTo, String name) throws UnsupportedEncodingException {
        final Email email = DefaultEmail.builder()
                .from(new InternetAddress("TraWell.customerservice@gmail.com",
                        "TraWell"))
                .to(newArrayList(
                        new InternetAddress(emailTo,name)))
                .subject("Serial Number")
                .body("Hi, " + name +" this is you serial number: "+ serialNumber)
                .encoding("UTF-8").build();

        emailService.send(email);
    }

    /**
     * This method create and send the email to an user
     * @author Umberto Russomando 
     * @param text
     * @param object
     * @param emailTo
     * @param name
     * @throws UnsupportedEncodingException
     */
    public void sendEmail(String text, String object, String emailTo, String name) throws UnsupportedEncodingException {
        final Email email = DefaultEmail.builder()
                .from(new InternetAddress("TraWell.customerservice@gmail.com",
                        "TraWell"))
                .to(newArrayList(
                        new InternetAddress(emailTo,name)))
                .subject(object)
                .body(text)
                .encoding("UTF-8").build();

        emailService.send(email);
    }

    /**
     * This method create and send a report email from an user
     * @author Umberto Russomando
     * @param text
     * @param object
     * @param emailFrom
     * @param name
     * @throws UnsupportedEncodingException
     */
    public void sendReportEmail(String text, String object, String emailFrom, String name) throws UnsupportedEncodingException {
        final Email email = DefaultEmail.builder()
                .from(new InternetAddress(emailFrom,
                name))
                .to(newArrayList(
                        new InternetAddress("TraWell.customerservice@gmail.com","TraWell")))
                .subject(object)
                .body(text)
                .encoding("UTF-8").build();

        emailService.send(email);
    }

}
