package com.udacity.jwdnd.c1.cloudstorage.controllers;

import com.udacity.jwdnd.c1.cloudstorage.models.User;
import com.udacity.jwdnd.c1.cloudstorage.orm.NoteMapper;
import com.udacity.jwdnd.c1.cloudstorage.services.FileService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final FileService fileService;
    private final NoteMapper noteMapper;

    public HomeController(FileService fileService, NoteMapper noteMapper) {
        this.fileService = fileService;
        this.noteMapper = noteMapper;
    }

    @GetMapping
    public String showPage(Authentication auth, Model model) {
        User user = (User) auth.getPrincipal();
        model.addAttribute("files", fileService.getFiles(user.getUserId()));
        model.addAttribute("notes", noteMapper.getNotes(user.getUserId()));

        return "home";
    }

}
