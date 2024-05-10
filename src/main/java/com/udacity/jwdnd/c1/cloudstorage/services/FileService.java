package com.udacity.jwdnd.c1.cloudstorage.services;

import com.udacity.jwdnd.c1.cloudstorage.models.File;
import com.udacity.jwdnd.c1.cloudstorage.orm.FileMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;

@Service
public class FileService {

    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public void save(MultipartFile file, Integer userId) throws IOException {
        // check if file existed then stop saving
        boolean isFileExisted = fileMapper.isExistedBy(file.getOriginalFilename(), userId);
        if (isFileExisted) {
            throw new FileAlreadyExistsException(file.getOriginalFilename());
        }

        // save file into database
        fileMapper.insert(new File(
                null,
                file.getOriginalFilename(),
                file.getContentType(),
                file.getSize(),
                userId,
                file.getBytes()
        ));
    }

    public List<File> getFiles(Integer userId) {
        return fileMapper.getFiles(userId);
    }
}
