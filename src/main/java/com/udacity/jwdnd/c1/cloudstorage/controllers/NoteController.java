package com.udacity.jwdnd.c1.cloudstorage.controllers;

import com.udacity.jwdnd.c1.cloudstorage.models.Note;
import com.udacity.jwdnd.c1.cloudstorage.models.User;
import com.udacity.jwdnd.c1.cloudstorage.orm.NoteMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/{noteId}")
    public String update(
            @PathVariable("noteId") Integer noteId,
            @ModelAttribute Note note,
            Authentication auth,
            Model model
    ) {
        String errorMessage = null;

        Integer userId = ((User) auth.getPrincipal()).getUserId();
        int effectedRows = noteMapper.update(new Note(noteId, note.getNoteTitle(), note.getNoteDescription(), userId));

        if (effectedRows != 1) {
            errorMessage = "Something went wrong. Please try again.";
        }

        model.addAttribute("anchor", "nav-notes");
        model.addAttribute("errorMessage", errorMessage);

        return "result";
    }

    @DeleteMapping("/{noteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable("noteId") Integer noteId,
            Authentication auth
    ) {
        Integer userId = ((User) auth.getPrincipal()).getUserId();
        noteMapper.delete(noteId, userId);
    }
}
