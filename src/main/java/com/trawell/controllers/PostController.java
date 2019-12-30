package com.trawell.controllers;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("post")
public class PostController{
    
    @GetMapping("/landing")
    public String landing() {
        return "pages/post/addPost";
    }
}