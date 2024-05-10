package com.udacity.jwdnd.c1.cloudstorage.controllers;

import com.udacity.jwdnd.c1.cloudstorage.models.User;
import com.udacity.jwdnd.c1.cloudstorage.services.FileService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

@Controller
@RequestMapping("/files")
public class FileStorageController {

    private final FileService fileService;

    public FileStorageController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping
    public String upload(
            @RequestParam("fileUpload") MultipartFile file,
            Authentication auth,
            Model model
    ) {
        String errorMessage = null;
        Integer userId = ((User) auth.getPrincipal()).getUserId();
        try {
            fileService.save(file, userId);
        } catch (FileAlreadyExistsException e) {
            System.out.println(e.getMessage());
            errorMessage = "File already existed. Please rename the file if you want to re-upload.";
        } catch (IOException e) {
            errorMessage = "File uploading failed. Please try again.";
        }

        model.addAttribute("anchor", "nav-files");
        model.addAttribute("errorMessage", errorMessage);

        return "result";
    }
}
