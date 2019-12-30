package com.trawell.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.trawell.models.Carsharing;
import com.trawell.models.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @author Alfieri Davide
 * 
 * carsharing:andranno mappate tutte le funzionalità relative al carsharing
 */

@Controller
@RequestMapping("carsharing")
public class CarsharingController {
    
    @GetMapping("/create")
    public String create() {

        return "pages/carsharing/createcarsharing";
    }

    @GetMapping("/change/{id}")
    public String change(HttpSession session, @PathVariable ("id") Long id, Model model){

        User user = (User) session.getAttribute("user");

        if (user == null ? false : user.getUserCreatedAdList() == null ? false : user.getUserCreatedAdList().size() > 0) {
            int index = user.getUserCreatedAdList().indexOf(new Carsharing(id));
            model.addAttribute("carsharing", user.getUserCreatedAdList().get(index));
        }

        return "pages/carsharing/modifycarsharing";
    }

    @GetMapping("/view/{id}")
    public String view(HttpSession session, @PathVariable("id") Long id, Model model) {
        
        User user = (User) session.getAttribute("user");
        Carsharing carsharing = new Carsharing(id);

        if (user == null ? false : user.getUserCreatedAdList() == null ? false : user.getUserCreatedAdList().size() > 0) {
            int index = user.getUserCreatedAdList().indexOf(carsharing);
            model.addAttribute("carsharing", user.getUserCreatedAdList().get(index));
        }
        
        return "pages/carsharing/viewcarsharing"; 
    }

    @GetMapping("/list-view")
    public String list(HttpSession session, Model model) {
        
        User user = (User) session.getAttribute("user");
        List<Carsharing> list = user.getUserCreatedAdList();

        if (list == null ? false : list.size() > 0) {
            
            model.addAttribute("isEmpty", true);
        } else {
            user.getUserCreatedAdList();
            model.addAttribute("isEmpty", true);
        }

        return "pages/carsharing/listcarsharing";
    }
}