package com.example.decablog.controller;

import com.example.decablog.model.UserModel;
import com.example.decablog.service.serviceImplementation.PostServiceImpl;
import com.example.decablog.service.serviceImplementation.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    private final UserServiceImpl userService;
    private final PostServiceImpl postService;

    public UserController(UserServiceImpl userService, PostServiceImpl postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("/signup")
    public String register(Model model) {
        model.addAttribute("registerRequest", new UserModel());
        return "sign_up";
    }


    @PostMapping("/sign_up")
    public String save(@ModelAttribute UserModel userModel) {
        UserModel user = new UserModel();
        user.setName(userModel.getName());
        user.setEmail(userModel.getEmail());
        user.setGender(userModel.getGender());

        UserModel userModel1 = userService.saveUser(userModel);
        System.out.println(userModel1);

        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserModel userModel, HttpSession session, Model model, Model model1) {
        System.out.println("Login Request: " + userModel);
        UserModel userModel1 = userService.login(userModel.getEmail(),userModel.getPassword());
        if((userModel1 != null) && (userModel1.getId() == 1)) {
            session.setAttribute("user", userModel1);
            model.addAttribute("userLogin", userModel1.getName());
            postService.viewHomePage(model1);
            return "adminhome";
        } else if (userModel1 != null){
            session.setAttribute("user", userModel1);
            model.addAttribute("userLogin", userModel1.getName());
            postService.viewHomePage(model1);
            return "home";
        } else {
            return "redirect:/";
        }
    }
}
