package com.trawell.controllers;

import javax.servlet.http.HttpSession;

import com.trawell.models.Group;
import com.trawell.models.User;
import com.trawell.models.Wallet;
import com.trawell.services.GroupService;
import com.trawell.services.WalletService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * WalletController: andranno mappate tutte le funzionalità relative al Wallet
 * 
 */

@Controller
@RequestMapping("Wallet")
public class WalletController {
    @Autowired
    private WalletService wallet;

    @Autowired
    private GroupService groupService;

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
        Group group = groupService.findOne(id);
        Wallet publicWallet = group.getWallet();
        Wallet userWallet = wallet.findUserWalletofGroup(id, idUser);
        /*
         * int n = 0; for (int x = 0; x < walletGroup.size(); x++) { Document d =
         * allDocuments.get(x); if ((d.isDocumentIsPrivate() && idUser == d.getIdUser())
         * || !(d.isDocumentIsPrivate())) { allDocumentsFinal.add(n,
         * allDocuments.get(x)); n++; } }
         * 
         * model.addAttribute("allDocumentsFinal", allDocumentsFinal);
         * session.setAttribute("walletGroup", walletGroup);
         */

        model.addAttribute("publicWallet", publicWallet);
        model.addAttribute("userWallet", userWallet);

        return "pages/user/walletGroup";
    }

}
