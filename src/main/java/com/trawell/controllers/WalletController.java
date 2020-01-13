package com.trawell.controllers;

import java.util.ArrayList;
import java.sql.Date;

import javax.servlet.http.HttpSession;

import com.trawell.models.Document;
import com.trawell.models.TrawellGroup;
import com.trawell.models.User;
import com.trawell.models.Wallet;
import com.trawell.services.DocumentService;
import com.trawell.services.TrawellGroupService;
import com.trawell.services.UserService;
import com.trawell.utilities.Encoder;
import com.trawell.utilities.uploader.UploadUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;

/**
 * WalletController: andranno mappate tutte le funzionalità relative al Wallet
 * 
 */

@Controller
@RequestMapping("Wallet")
public class WalletController {

    @Autowired
    private TrawellGroupService groupService;

    @Autowired
    private DocumentService documentDao;

    @Autowired
    private UserService userDao;

    /**
     * This method allows a admin to view a Wallet for a specific User
     * 
     * @author Gaetano Ruggiero
     * @param complaints
     * @param allComplaint
     * @param c
     * @param n
     */

    @GetMapping("/walletGroup")
    public String viewAllDocument(HttpSession session, @RequestParam(required = true, name = "id") Long id,
            Model model) {

        User user = (User) session.getAttribute("user");
        long idUser = user.getId();
        TrawellGroup trawellGroup = groupService.findOne(id);
        if (trawellGroup != null) {
            Wallet publicWallet = trawellGroup.getPublicWallet();
            Wallet userWallet = trawellGroup.getAllWallets().stream()
                    .filter(x -> x.getUser().getId() == null ? false : x.getUser().getId() == idUser).findFirst().orElse(null);

            model.addAttribute("publicWallet", publicWallet);

            model.addAttribute("userWallet", userWallet);

            session.setAttribute("idGroup", id);

            return "pages/user/walletGroup";
        } else {
            return "pages/error";
        }
    }


    @PostMapping("/addDocument")
    public String addDocument(@RequestParam(name = "files", required = true) MultipartFile[] files,@RequestParam(name = "dueDate", required = true) Date dueDate, HttpSession session, Model model)
    {
        User user = (User) session.getAttribute("user");        

        ArrayList<Document> documents = new  ArrayList<Document>();

        if(user != null)
        {
            int i = 0;

            for (MultipartFile file : files)
            {

               Document document = new Document();

                Encoder encoder = new Encoder();

                String uploadDir = encoder.encoding(user.getUsername(), 3);
                String fileName = UploadUtils.getCurrentTimeUsingDate()+i;

                document.setPath(UploadUtils.uploadDocument(file,uploadDir,fileName));
                Wallet wallet = user.getUserWallets().stream().filter(x -> x.getUser() != null).findFirst().orElse(null);
                document.setWallet(wallet);
                document.setDueDate(dueDate);
                document.setIdUser(user.getId());
                document.setName(fileName);
                documents.add(document);
                

                documentDao.create(document);
                userDao.update(user);

                i++;
            }

            Long idGroup = (Long) session.getAttribute("idGroup");
            return "redirect:/Wallet/walletGroup"+"?id="+idGroup;
        }

        return "pages/user/login";
    }

}
