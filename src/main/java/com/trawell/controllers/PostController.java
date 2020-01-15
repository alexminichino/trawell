package com.trawell.controllers;

import com.trawell.services.PostService;
import com.trawell.services.TrawellGroupService;
import com.trawell.services.UserService;
import com.trawell.models.Photo;
import com.trawell.models.Post;
import com.trawell.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpSession;

import com.trawell.utilities.Encoder;
import com.trawell.utilities.uploader.UploadUtils;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author Umberto Russomando
 * Questo controller mapperà tutte le funzionalità MVC relative ai post 
 */

@Controller
@RequestMapping("post")
public class PostController{

    @Autowired
    private PostService dao;

    @Autowired
    TrawellGroupService daoGroup;

    @Autowired
    UserService userDao;

    /**
     * @author Umberto Russomando
     * Questo metodo permette di raggiungere la pagina per creare i post
    */
    @GetMapping("/landing")
    public String landing() {
        return "pages/post/addPost";
    }

    /**
     * @author Umberto Russomando
     * This method let you create a post and save it into the db
     * @param postDescription
     * @param post
     * @param session
     * @return redirects the user to the page that shows you all the posts
     */
    @PostMapping("/addPost")
    public String createPost(@RequestParam(name="postDescription", required=true)  String postDescription,
    @RequestParam(name = "files", required = true) MultipartFile[] files,
            @ModelAttribute  Post post,  HttpSession session,@RequestParam(name="idGroup", required=false)  Long idGroup)
    {
        User user = (User) session.getAttribute("user");
        ArrayList<Photo> photos = new ArrayList<Photo>();



        int i = 0;

        //Carica i file
        for (MultipartFile file : files) {

            Photo photo = new Photo();

            Encoder encoder = new Encoder();

            String uploadDir = encoder.encoding(user.getMail(), 3);
            String fileName = UploadUtils.getCurrentTimeUsingDate()+i;
            fileName = encoder.encoding(fileName, 1);
            photo.setPath(UploadUtils.uploadPhoto(file,uploadDir,fileName));
            photo.setPost(post);
            
            photos.add(photo);

            i++;
        }



        //Setta il post
        post.setUser(user);
        post.setPostDescription(postDescription);
        post.setPhotos(photos);
        
        if(idGroup != 0)
        {
            post.setGroup(daoGroup.findOne(idGroup));
            dao.create(post);
            return "redirect:/group/view"+"?id="+idGroup;
        }
        

        //Crea il post nel db
        dao.create(post);

        userDao.update(user);

        return "redirect:/post/viewPosts";
    }

    /**
     * This method lets you see all the public posts
     * @author Umberto Russomando
     * @param session
     * @param model
     * @return redirects the user to the page that shows you all the posts
     */
    @GetMapping("/viewPosts")
    public String viewPosts(HttpSession session, Model model) {

        User user = (User) session.getAttribute("user");

        if(user != null)
        {
            Collection<Post> posts =  dao.findByIdGroupIsNull();

            posts.parallelStream().forEach(p->{p.getUser();});

            model.addAttribute("posts", posts);


            return "redirect:/";
        }
        else
        {
            return "pages/error";
        }

        
    }

    /**
     * This method let you see a single reported post
     * @author Umberto Russomando
     * @param session
     * @param model
     * @param idPost
     * @return the url of the view used to view displaying the post
     */
    @GetMapping("/viewReportedPost")
    public String viewReportedPost(HttpSession session, Model model, @RequestParam(required = true, name="id") Long idPost)
    {
        User user = (User) session.getAttribute("user");

        if(user == null)
        {
            return "pages/error";
        }

        Post post = dao.findOne(idPost);

        if(post.isReported())
        {
            model.addAttribute("post", post);
        }
        else
        {
            model.addAttribute("post", null);
        }

        return "pages/post/postView";
    }

    /**
     * This method lists all the reported post in a table
     * @author Umberto Russomando
     * @param session
     * @param model
     * @return the url of the view used to view displaying the posts
     */
    @GetMapping("/listReportedPosts")
    public String listReportedPosts(HttpSession session, Model model) {

        User user = (User) session.getAttribute("user");

        if(user == null || !user.getIsAdmin())
        {
            return "pages/error";
        }

        Collection<Post> posts =  dao.findReportedPosts(); 
        
        posts.parallelStream().forEach(p->{p.getUser();});

        model.addAttribute("posts", posts);

        return "pages/post/listReportedPosts";
    }
    

}