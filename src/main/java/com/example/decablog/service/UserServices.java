package com.example.decablog.service;

import com.example.decablog.model.UserModel;
import org.springframework.stereotype.Service;

@Service
public interface UserServices {
    UserModel saveUser(UserModel userModel);
    UserModel login(String email, String password);
}
