package com.udacity.jwdnd.c1.cloudstorage.services;

import com.udacity.jwdnd.c1.cloudstorage.models.File;
import com.udacity.jwdnd.c1.cloudstorage.orm.FileMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileService {

    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public void save(MultipartFile file, Integer userId) {
        try {
            // save file into database
            fileMapper.insert(new File(
                    null,
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getSize(),
                    userId,
                    file.getBytes()
            ));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
