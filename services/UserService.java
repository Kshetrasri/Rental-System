package com.crio.rentvideo.services;

import com.crio.rentvideo.security.JwtTokenUtil;
import com.crio.rentvideo.models.User;
import com.crio.rentvideo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;  // Inject JwtTokenUtil

    public User getUserFromToken(String token) {
        String username = jwtTokenUtil.extractUsername(token);
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return user;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
