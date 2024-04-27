package com.sherlock.joyblunder.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sherlock.joyblunder.models.User;
import com.sherlock.joyblunder.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Registration Page
    @GetMapping("/") public String login(@ModelAttribute("user") User user){return "login.jsp";}
    
    @PostMapping("/register") public String register(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
        User isUser = userService.findByEmail(user.getEmail());
        if (isUser != null) {
            result.rejectValue("email", "Unique", "Email already on use");
        }
        if(!user.getPassword().equals(user.getPasswordConfirm())) {
            result.rejectValue("passwordConfirm", "Matches", "Passwords do not match");
        }
        if (result.hasErrors()) {
            return "login.jsp";
        }
        User u = userService.registerUser(user);
        session.setAttribute("userId", u.getId());
        return "redirect:/home";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, @RequestParam("_email") String email, @RequestParam("_password") String password, Model model, HttpSession session){
        boolean isAuthenticated = userService.authenticateUser(email, password);
        if (isAuthenticated) {
            User u = userService.findByEmail(email);
            session.setAttribute("userId", u.getId());
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Invalid Credentials. Please try again.");
            return "login.jsp";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
