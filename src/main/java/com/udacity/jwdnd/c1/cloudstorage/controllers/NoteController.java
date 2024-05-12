package com.udacity.jwdnd.c1.cloudstorage.controllers;

import com.udacity.jwdnd.c1.cloudstorage.models.Note;
import com.udacity.jwdnd.c1.cloudstorage.models.User;
import com.udacity.jwdnd.c1.cloudstorage.orm.NoteMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notes")
public class NoteController {

    private final NoteMapper noteMapper;

    public NoteController(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    @PostMapping
    public String create(
            @ModelAttribute Note note,
            Authentication auth,
            Model model
    ) {
        String errorMessage = null;

        Integer userId = ((User) auth.getPrincipal()).getUserId();
        int addedRows = noteMapper.insert(new Note(null, note.getNoteTitle(), note.getNoteDescription(), userId));

        if (addedRows < 1) {
            errorMessage = "Something went wrong. Please try again.";
        }

        model.addAttribute("anchor", "nav-notes");
        model.addAttribute("errorMessage", errorMessage);

        return "result";
    }
}
