package com.example.decablog.service.serviceImplementation;

import com.example.decablog.model.UserModel;
import com.example.decablog.repository.UserRepository;
import com.example.decablog.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class UserServiceImpl implements UserServices {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserModel saveUser(UserModel userModel) {
        if (userRepository.findByEmail(userModel.getEmail()).isPresent()) {
            System.out.println("You can't use this mail again");
            return null;
        }
        return userRepository.save(userModel);
    }


    @Override
    public UserModel login(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password).orElse(null);
    }
}
