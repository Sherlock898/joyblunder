package com.sherlock.joyblunder.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sherlock.joyblunder.models.BabyName;
import com.sherlock.joyblunder.models.User;
import com.sherlock.joyblunder.services.BabyNameService;
import com.sherlock.joyblunder.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class BabyController {
    private final BabyNameService babyNameService;
    private final UserService userService;

    public BabyController(BabyNameService babyNameService, UserService userService) {
        this.babyNameService = babyNameService;
        this.userService = userService;
    }

    //Home page
    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return "redirect:/";
        model.addAttribute("babyNames", babyNameService.getAll());
        model.addAttribute("user", userService.findById(userId));
        return "home.jsp";
    }

    //New Name
    @GetMapping("/names/new")
    public String newBabyName(@ModelAttribute("babyName") BabyName babyName, HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return "redirect:/";
        return "newName.jsp";
    }

    //Create Name
    @PostMapping("/names/new")
    public String createBabyName(@Valid @ModelAttribute("babyName") BabyName babyName, BindingResult result, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return "redirect:/";
        BabyName isName = babyNameService.findByName(babyName.getName());
        if (isName != null) {
            result.rejectValue("name", "Unique", "Name already registered");
        }
        if (result.hasErrors()) return "newName.jsp";
        babyName.setUser(userService.findById(userId));

        babyNameService.createBabyName(babyName);
        return "redirect:/home";
    }

    //Show Name
    @GetMapping("/names/{id}")
    public String showBabyName(@PathVariable("id") Long id, HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return "redirect:/";
        BabyName babyName = babyNameService.findById(id);
        if (babyName == null) return "redirect:/home";
        model.addAttribute("babyName", babyName);
        model.addAttribute("votedForThis", userService.findById(userId).getVotedBabyNames().contains(babyName));
        model.addAttribute("isOwner", babyName.getUser().getId().equals(userId));
        return "showName.jsp";
    }

    //Edit name
    @GetMapping("/names/{id}/edit")
    public String editBabyName(@PathVariable("id") Long id, @ModelAttribute("babyName") BabyName babyName, HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return "redirect:/";
        BabyName babyNameToEdit = babyNameService.findById(id);
        if (babyNameToEdit == null) return "redirect:/home";
        if (!babyNameToEdit.getUser().getId().equals(userId)) return "redirect:/home";
        model.addAttribute("name", babyNameToEdit);
        return "editName.jsp";
    }

    @PostMapping("/names/{id}/edit")
    public String updateBabyName(@PathVariable("id") Long id, @Valid @ModelAttribute("babyName") BabyName babyName, BindingResult result, HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return "redirect:/";
        BabyName babyNameToEdit = babyNameService.findById(id);
        if (babyNameToEdit == null) return "redirect:/home";
        if (!babyNameToEdit.getUser().getId().equals(userId)) return "redirect:/home";
        if (result.hasErrors()){
            model.addAttribute("name", babyNameToEdit);
            return "editName.jsp";
        }
        babyNameToEdit.setGender(babyName.getGender());
        babyNameToEdit.setMeaning(babyName.getMeaning());
        babyNameToEdit.setOrigin(babyName.getOrigin());
        
        babyNameService.updateBabyName(babyNameToEdit);
        return "redirect:/home";
    }

    //Delete name
    @PostMapping("/names/{id}/delete")
    public String deleteBabyName(@PathVariable("id") Long id, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return "redirect:/";
        BabyName babyNameToDelete = babyNameService.findById(id);
        if (babyNameToDelete == null) return "redirect:/home";
        if (!babyNameToDelete.getUser().getId().equals(userId)) return "redirect:/home";
        babyNameService.deleteBabyName(id);
        return "redirect:/home";
    }

    //Vote name
    @PostMapping("/names/{id}/vote")
    public String voteBabyName(@PathVariable("id") Long id, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return "redirect:/home";
        BabyName babyNameToVote = babyNameService.findById(id);
        if (babyNameToVote == null) return "redirect:/home";
        User voter = userService.findById(userId);
        if (babyNameToVote.getVoters().contains(voter)) return "redirect:/home";
        babyNameService.voteBabyName(babyNameToVote, voter);
        return "redirect:/home";
    }
}
