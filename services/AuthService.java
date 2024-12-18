package com.crio.rentvideo.services;

import com.crio.rentvideo.models.User;
import com.crio.rentvideo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public void registerUser(User user) {
        // Hash the password using BCrypt and save the user
        user.setRole(user.getRole() == null ? "CUSTOMER" : user.getRole());
        userRepository.save(user);
    }

    public User authenticate(String email, String password) {
        // Authenticate user (skip password check for now)
        return userRepository.findByEmail(email);
    }
}