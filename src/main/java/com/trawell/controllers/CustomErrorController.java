package com.trawell.controllers;
import javax.servlet.http.HttpSession;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class CustomErrorController implements ErrorController {

  // override di errror controller

  @RequestMapping("/error")
  public ModelAndView handleError(HttpSession session) {
    ModelAndView modelAndView = new ModelAndView();
    if(session.getAttribute("user")==null)
      modelAndView.setViewName("pages/user/login");
    else
      modelAndView.setViewName("error");
    return modelAndView;
  }

  @Override
  public String getErrorPath() {
    return "/error";
  }
}