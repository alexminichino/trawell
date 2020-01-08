/*
package com.trawell.controllers;

import javax.servlet.http.HttpSession;

import com.trawell.models.User;
import com.trawell.utilities.uploader.UploadUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("uploadfile")
public class FileUploaderController {

    @RequestMapping("/uploadPhoto")
    public void uploadPhoto(HttpSession session,@RequestParam(name = "files", required = true) MultipartFile[] files) {
       
        User user = (User) session.getAttribute("user");
        
        for (MultipartFile file : files) {
            String uploadDir = user.getName()+user.getName();
            String fileName = "";
            UploadUtils.uploadPhoto(file,uploadDir,fileName);
        }
        
      
    }
    
}






*/