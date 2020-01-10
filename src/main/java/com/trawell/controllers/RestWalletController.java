package com.trawell.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.trawell.models.Document;
import com.trawell.models.User;
import com.trawell.models.Wallet;
import com.trawell.services.DocumentService;
import com.trawell.services.WalletService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ruggiero Gaetano RestWalletController andranno mappate tutte le
 *         funzionalità relative al wallet e document per la comunicazione REST
 *
 */
@RestController
@RequestMapping(value = "/api")
public class RestWalletController {
    @Autowired
    DocumentService daoD;

    @Autowired
    WalletService daoW;

    /**
     * The method maps "/api/document/changeVisibility" post request that allows to
     * update the visibility of a document.
     * 
     * @author Ruggiero Gaetano
     * @param user    
     * @param session
     * @param id
     * @param wallet
     * @param d
     * 
     * @return a 200 HttpResponse if deletion was successful, 403 if user doesn't
     *         have the permission and 500 otherwise
     */
    @RequestMapping(value = "/document/changeVisibility/{id}", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Document> modify(HttpSession session, @PathVariable("id") Long id) {
        User user = (User) session.getAttribute("user");
        Document d = daoD.findOne(id);
        Document updateDocument = null;
        boolean b = d.getWallet().isPrivate();
        if (user.getId() == d.getIdUser()) {
            if (b == true) {
                Wallet w = d.getWallet().getGroup().getPublicWallet();
                d.setWallet(w);
                updateDocument = daoD.update(d);
            } else {
                List<Wallet> list = d.getWallet().getGroup().getAllWallets();
                Wallet w = list.stream().filter(x -> x.getIdOwner().equals(user.getId())).findFirst().orElse(null);
                if (w != null) {
                    d.setWallet(w);
                    updateDocument = daoD.update(d);
                } else
                    return new ResponseEntity<Document>(HttpStatus.FORBIDDEN);
            }
            return updateDocument == null ? new ResponseEntity<Document>(HttpStatus.INTERNAL_SERVER_ERROR)
                    : new ResponseEntity<Document>(HttpStatus.OK);
        }
        return new ResponseEntity<Document>(HttpStatus.FORBIDDEN);
    }

    /**
     * The method maps "/api/document/eliminate/{id}" post request that allows to
     * delete a document from the Wallet list.
     * 
     * @param session
     * @param id
     * @param user
     * @param wallet
     * @param d
     * @return a 200 HttpResponse if deletion was successful, 403 if user doesn't
     *         have the permission and 500 otherwise
     */
    @RequestMapping(value = "/document/eliminate/{id}", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Document> delete(HttpSession session, @PathVariable("id") Long id) {
        User user = (User) session.getAttribute("user");

        if (user != null) {
            Document d = daoD.findOne(id);
            if (user.getId() == d.getIdUser() || user.getId() == d.getWallet().getIdOwner()) {
                d.getPath();// bisogna cancellare anche nella cartella
                daoD.delete(id);
                return new ResponseEntity<Document>(HttpStatus.OK);
            } else
                return new ResponseEntity<Document>(HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<Document>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}