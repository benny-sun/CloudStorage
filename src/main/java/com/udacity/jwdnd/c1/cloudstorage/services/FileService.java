package com.udacity.jwdnd.c1.cloudstorage.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileService {

    public void save(MultipartFile file) {
        String dir = System.getProperty("user.dir") + "/upload";
        try {
            // create directory if not exists
            Files.createDirectories(Paths.get(dir));
            // save file
            file.transferTo(new File(dir + "/" + file.getOriginalFilename()));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
