package com.example.decablog.controller;

import com.example.decablog.model.Post;
import com.example.decablog.model.UserModel;
import com.example.decablog.service.serviceImplementation.PostServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

@Controller
public class PostController {

    private PostServiceImpl postService;

    public PostController(PostServiceImpl postService) {
        this.postService = postService;
    }

    @GetMapping("/post")
    public String postPage(HttpSession session) {
        UserModel userModel = (UserModel) session.getAttribute("user");
//        System.out.println("Session user: " + userModel.getName());
        return "post";
    }

    @GetMapping("/adminhome")
    public String returnadmin(Model model) {
        List<Post> posts = postService.getAllPost();
        Collections.reverse(posts);
        model.addAttribute("posts", posts);


        return "adminhome";
    }

    @GetMapping("/home")
    public String returnhome(Model model)  {
        List<Post> posts = postService.getAllPost();
        Collections.reverse(posts);
        model.addAttribute("posts", posts);

        return "home";
    }

    @GetMapping("/delete/{postId}")
    public String deletePost(@PathVariable String postId, HttpSession session) {
        UserModel userModel = (UserModel) session.getAttribute("user");
        Post post = postService.getPostById(Long.parseLong(postId));
        postService.deletePost(post);

        return "redirect:/adminhome";
    }

    @GetMapping("/editpost/{postId}")
    public String editPost(@PathVariable(value = "id") Long id, Model model) {
        Post post = postService.getPostById(id);

        model.addAttribute("post", post);

        return "editpost";
    }

    @PostMapping("/update/{postId}")
    public String updatePost(@PathVariable String id, @RequestParam(value = "content") String content, @RequestParam(value = "title") String title, @RequestParam(value = "intro") String intro, Model model) {
        Post post = postService.getPostById(Long.parseLong(id));
        post.setTitle(title);
        post.setIntro(intro);
        post.setContent(content);

        postService.createPost(post);

        postService.viewHomePage(model);

        return "redirect:/adminhome";
    }


    @PostMapping("/creates")
    public String createPost(@ModelAttribute Post post, HttpSession session, Model model) {
        UserModel userModel = (UserModel) session.getAttribute("user");
        Post post1 = new Post();

        post1.setTitle(post.getTitle());
        post1.setIntro(post.getIntro());
        post1.setContent(post.getContent());

        postService.createPost(post1);
        postService.viewHomePage(model);

        return "redirect:/adminhome";
    }

}
