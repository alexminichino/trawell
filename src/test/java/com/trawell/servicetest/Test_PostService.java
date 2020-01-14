package com.trawell.servicetest;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.trawell.models.Post;
import com.trawell.models.TrawellGroup;
import com.trawell.models.User;
import com.trawell.repositories.PostRepository;
import com.trawell.services.PostService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class Test_PostService{
    @InjectMocks
    PostService dao;

    @Mock
    PostRepository repo;

    Post post;
    Post newpost;

    @Before public void init(){
        post= new Post();
        newpost= new Post();
        User user = new User();
        TrawellGroup group =new TrawellGroup();
        group.setId(1L);
        post.setId(1L);
        post.setIsReported(true);
        post.setUser(user);
        post.setGroup(group);
        post.setPostDescription("bla bla bla in realta l'Australia non esiste");

        newpost.setGroup(group);
        newpost.setIsReported(true);
        newpost.setUser(user);
        newpost.setPostDescription("bla bla bla in realtà mi scoccio di inventare sta volta ");

    }

    @Test
    public void TC_post(){

        when(repo.save(any(Post.class))).thenReturn(post);

        assertEquals(Long.valueOf(1L) ,dao.create(newpost).getId());

    }

    @Test
    public void TC_post2(){

        assertEquals(null, dao.create(post));
    }

    @Test
    public void TC_post3(){
        Post savepost= new Post();
        User user = new User();
        TrawellGroup group =new TrawellGroup();
        group.setId(1L);
        savepost.setId(1L);
        savepost.setIsReported(false);
        savepost.setUser(user);
        savepost.setGroup(group);
        savepost.setPostDescription("bla bla bla in realta Umberto sta forkando ");

        when(repo.findById(Long.valueOf(1L))).thenReturn(Optional.of(savepost));
        when(repo.save(any(Post.class))).thenReturn(post);

        assertEquals(post ,dao.update(post));
    }

    @Test
    public void TC_post4(){

        when(repo.findById(Long.valueOf(1L))).thenReturn(Optional.empty());

        assertEquals(null ,dao.update(post));
    }

    @Test
    public void TC_post5(){

        ArrayList<Post> list = new ArrayList<>();
        list.add(post);

        when(repo.findAll()).thenReturn(list);
        Collection<Post> collection = dao.findAll();

        assertEquals(true, collection.size() == 1 && collection.contains(post));
    }

    @Test
    public void TC_post6(){
        when(repo.findById(1L)).thenReturn(Optional.of(post));
        dao.delete(1L);
        Mockito.verify(repo, times(1)).delete(post);
    }
}