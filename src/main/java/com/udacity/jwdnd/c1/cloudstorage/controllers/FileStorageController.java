package com.udacity.jwdnd.c1.cloudstorage.controllers;

import com.udacity.jwdnd.c1.cloudstorage.models.File;
import com.udacity.jwdnd.c1.cloudstorage.models.User;
import com.udacity.jwdnd.c1.cloudstorage.services.FileService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

    @GetMapping("/{fileId}")
    public ResponseEntity<byte[]> download(
            @PathVariable("fileId") Integer fileId,
            Authentication auth
    ) throws FileNotFoundException, UnsupportedEncodingException {
        Integer userId = ((User) auth.getPrincipal()).getUserId();
        File file = fileService.getFile(fileId, userId);

        if (file == null) throw new FileNotFoundException();

        HttpHeaders headers = new HttpHeaders();
        String encodedFilename = URLEncoder.encode(file.getFilename(), "UTF8"); // to support unicode out of range
        headers.setContentDispositionFormData("attachment", encodedFilename); // tell the browser to open file as an attachment
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); // download as binary stream

        return new ResponseEntity<byte[]>(file.getFileData(), headers, HttpStatus.CREATED);
    }

    @DeleteMapping("/{fileId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable("fileId") Integer fileId,
            Authentication auth
    ) {
        Integer userId = ((User) auth.getPrincipal()).getUserId();
        fileService.deleteFile(fileId,userId);
    }
}
