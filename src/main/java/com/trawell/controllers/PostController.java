package com.trawell.controllers;

import com.trawell.services.PostService;
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


import java.util.Collection;

import javax.servlet.http.HttpSession;

/**
 * @author Umberto Russomando
 * Questo controller mapperà tutte le funzionalità MVC relative ai post 
 */

@Controller
@RequestMapping("post")
public class PostController{

    @Autowired
    private PostService dao;


    /**
     * @author Umberto Russomando
     * Questo metodo TEMPORANEO permette di raggiungere la pagina per creare i post
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
    public String createPost(@RequestParam(name="postDescription", required=true) final String postDescription,
            @ModelAttribute final Post post, final HttpSession session)
    {
        final User user = (User) session.getAttribute("user");
        post.setUser(user);
        post.setPostDescription(postDescription);

        dao.create(post);

        return "redirect:/post/viewPosts";
    }

    /**
     * @author Umberto Russomando
     * @param session
     * @param model
     * @return redirects the user to the page that shows you all the posts
     */
    @GetMapping("/viewPosts")
    public String viewPosts(final HttpSession session, final Model model) {

        final Collection<Post> posts =  dao.findByIdGroupIsNull();

        posts.parallelStream().forEach(p->{p.getUser();});

        model.addAttribute("posts", posts);


        return "pages/post/bachecapost";
    }

    @GetMapping("viewPost")
    public String viewPost(HttpSession session, Model model, @RequestParam(required = true, name="id") Long idPost)
    {
        Post post = dao.findOne(idPost);

        model.addAttribute("post", post);

        return "pages/post/postView";
    }

}