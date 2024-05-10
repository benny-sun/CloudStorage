package com.udacity.jwdnd.c1.cloudstorage.models;

import java.io.IOException;

public class File {
    private final Integer fileId;
    private final String filename;
    private final String contentType;
    private final String filesize;
    private final Integer userId;
    private final byte[] fileData;

    public File(Integer fileId, String filename, String contentType, Long filesize, Integer userId, byte[] fileData) throws IOException {
        this.fileId = fileId;
        this.filename = filename;
        this.contentType = contentType;
        this.filesize = Long.toString(filesize);
        this.userId = userId;
        this.fileData = fileData;
    }

    public Integer getFileId() {
        return fileId;
    }

    public String getFilename() {
        return filename;
    }

    public String getContentType() {
        return contentType;
    }

    public String getFilesize() {
        return filesize;
    }

    public Integer getUserId() {
        return userId;
    }

    public byte[] getFileData() {
        return fileData;
    }
}
