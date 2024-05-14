package com.udacity.jwdnd.c1.cloudstorage.controllers;

import com.udacity.jwdnd.c1.cloudstorage.models.User;
import com.udacity.jwdnd.c1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/signup")
public class SignupController {

    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showSignupPage() {
        return "/signup";
    }

    @PostMapping
    public String register(@ModelAttribute User user, Model model, RedirectAttributes redirectAttributes) {
        String errorMessage = null;

        if (userService.isUserExist(user)) {
            errorMessage = "User already registered. Please login.";
        } else if (userService.createUser(user) < 1) {
            errorMessage = "Signup error, please try again.";
        }

        if (errorMessage == null) {
            redirectAttributes.addFlashAttribute("signupSuccess", true);
            return "redirect:/login";
        } else {
            model.addAttribute("errorMessage", errorMessage);
            return "/signup";
        }
    }

}
