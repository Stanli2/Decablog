package com.example.decablog.service.serviceImplementation;

import com.example.decablog.model.Comments;
import com.example.decablog.model.Post;
import com.example.decablog.repository.CommentRepository;
import com.example.decablog.repository.PostRepository;
import com.example.decablog.service.PostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.List;

@org.springframework.stereotype.Service
public class PostServiceImpl implements PostServices {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }


    @Override
    public void createPost(Post post) {
        postRepository.save(post);
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.findPostById(id);
    }

    @Override
    public void deletePost(Post post) {
        postRepository.delete(post);
    }

    @Override
    public List<Post> getAllPost() {
        return postRepository.findAll();
    }


    public void viewHomePage(Model model){
        Post post = new Post();
        Comments comments = new Comments();
        CommentServiceImpl serviceImplementation = new CommentServiceImpl(commentRepository);
        List<Post> posts = this.getAllPost();
        Collections.reverse(posts);
        model.addAttribute("posts", posts);
        List<Comments> listOfComment = serviceImplementation.getAllComments();


        model.addAttribute("post", post);
        model.addAttribute("comment", comments);
        model.addAttribute("comments", listOfComment);


    }
}
