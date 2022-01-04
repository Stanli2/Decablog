package com.example.decablog.controller;

import com.example.decablog.model.Comments;
import com.example.decablog.model.Post;
import com.example.decablog.model.UserModel;
import com.example.decablog.service.serviceImplementation.CommentServiceImpl;
import com.example.decablog.service.serviceImplementation.PostServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class CommentController {

    private final CommentServiceImpl commentService;
    private final PostServiceImpl postService;

    public CommentController(CommentServiceImpl commentService, PostServiceImpl postService) {
        this.commentService = commentService;
        this.postService = postService;
    }


    @GetMapping("/createcomment/{id}")
    public String commentForm(@PathVariable(value = "id") Long id, Model model) {
        Post post = postService.getPostById(id);
        model.addAttribute("post", post);
        System.out.println("code reach here");
        return "comment_form";
    }


    @GetMapping("/read/{id}")
    public String readPost(@PathVariable(value = "id") Long id, Model model, Model model1) {
        Post post = postService.getPostById(id);
        model.addAttribute("post", post);

//        postService.viewHomePage(model1);
        return "read";
    }

    @GetMapping("/adminread/{id}")
    public String adminreadPost(@PathVariable(value = "id") Long id, Model model, Model model1) {
        Post post = postService.getPostById(id);
        model.addAttribute("post", post);

//        postService.viewHomePage(model1);
        return "adminread";
    }


    @PostMapping("/createcomment/{id}")
    public String createComment(@ModelAttribute Comments comments, HttpSession session, @PathVariable(value = "id") Long id, Model model) {
        UserModel userModel = (UserModel) session.getAttribute("user");
        Post post = postService.getPostById(id);
        Comments comments1 = new Comments();

        comments1.setUserModel(userModel);
        comments1.setPost(post);
        comments1.setComment(comments.getComment());

        commentService.createComment(comments1);
        postService.viewHomePage(model);

        return "read";
    }


    @GetMapping("/deletecomment/{commentId}")
    public String deleteComment(@PathVariable String commentId, HttpSession session, Model model) {
        UserModel userModel = (UserModel) session.getAttribute("user");
        Comments comments = commentService.getCommentById(Long.parseLong(commentId));
        boolean validUser = comments.getUserModel().equals(userModel);
        if (validUser){
            commentService.deleteComment(comments);
        }
        postService.viewHomePage(model);
        return "redirect:/read";
    }

    @GetMapping("/editComment/{commentId}")
    public String editCommentForm(@PathVariable(value = "commentId") Long commentId, Model model) {
        Comments comments = commentService.getCommentById(commentId);

        model.addAttribute("comment", comments);

        return "editComment";
    }

    @PostMapping("/updateComment/{commentId}")
    public String updateComment(@PathVariable String commentId, HttpSession session, @RequestParam(value = "comment") String comment, Model model) {
        UserModel userModel = (UserModel) session.getAttribute("user");
        Comments comments = commentService.getCommentById(Long.parseLong(commentId));
        boolean validOwner = comments.getUserModel().equals(userModel);
        if (validOwner) {
            comments.setComment(comment);
            commentService.createComment(comments);

        }
        postService.viewHomePage(model);
        return "redirect:/read";
    }
}
