package com.example.decablog.service;

import com.example.decablog.model.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostServices {

    void createPost (Post post);

    Post getPostById (Long id);

    void deletePost (Post post);

    List<Post> getAllPost();
}
