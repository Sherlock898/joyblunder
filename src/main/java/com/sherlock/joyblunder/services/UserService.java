package com.sherlock.joyblunder.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.sherlock.joyblunder.models.User;
import com.sherlock.joyblunder.repositories.UserRepository;

import jakarta.validation.Valid;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }    

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User registerUser(@Valid User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepository.save(user);
    }

    public boolean authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return false;
        }
        return BCrypt.checkpw(password, user.getPassword());
    }

    public User findById(Long userId) {
        return (User) userRepository.findById(userId).orElse(null);
    }
    
}
