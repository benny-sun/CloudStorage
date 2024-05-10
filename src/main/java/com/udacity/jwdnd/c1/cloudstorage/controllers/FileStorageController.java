package com.udacity.jwdnd.c1.cloudstorage.controllers;

import com.udacity.jwdnd.c1.cloudstorage.models.User;
import com.udacity.jwdnd.c1.cloudstorage.services.FileService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/files")
public class FileStorageController {

    private final FileService fileService;

    public FileStorageController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping
    public String upload(@RequestParam("fileUpload") MultipartFile file, Authentication auth) {
        Integer userId = ((User) auth.getPrincipal()).getUserId();
        fileService.save(file, userId);
        return "/home";
    }
}
