package com.trawell.controllers;

import javax.servlet.http.HttpSession;

import com.trawell.models.TrawellGroup;
import com.trawell.models.User;
import com.trawell.models.Wallet;
import com.trawell.services.TrawellGroupService;

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
    private TrawellGroupService groupService;

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
        Wallet publicWallet = trawellGroup.getPublicWallet();
        Wallet userWallet = trawellGroup.getAllWallets().stream()
                .filter(x -> x.getIdOwner() == null ? false : x.getIdOwner() == idUser).findFirst().orElse(null);

        model.addAttribute("publicWallet", publicWallet);

        model.addAttribute("userWallet", userWallet);

        return "pages/user/walletGroup";
    }

}
