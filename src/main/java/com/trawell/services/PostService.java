package com.trawell.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.trawell.models.Post;
import com.trawell.repositories.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService implements IPostService {

    @Autowired
    private PostRepository postRepository;


    @Override
    public Collection<Post> findAll() {
        ArrayList<Post> posts = new ArrayList<>();
        postRepository.findAll().forEach(posts::add);
        return posts;
    }

    @Override
    public Post findOne(Long id) {
        Optional<Post> post = postRepository.findById(id);
        return post.get();
    }

    @Override
    public Post create(Post post) {
        if (post.getId() != null) {
            //cannot create Post with specified Id value
            return null;
        }
        Post savedPost = postRepository.save(post);
        return savedPost;
    }

    @Override
    public Post update(Post post) {
        Post PostPersisted = findOne(post.getId());
        if (PostPersisted == null) {
            //cannot find Post with specified Id value
            return null;
        }
        Post updatedPost = postRepository.save(post);
        return updatedPost;
    }

    @Override
    public void delete(Long id) {
        postRepository.delete(findOne(id));
    }

}