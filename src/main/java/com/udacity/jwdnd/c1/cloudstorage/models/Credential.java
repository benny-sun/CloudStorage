package com.udacity.jwdnd.c1.cloudstorage.models;

public class Credential {

    private Integer credentialId;
    private String url;
    private String username;
    private String key;
    private String password;
    private String decodedPassword;
    private Integer userId;

    public Credential(Integer credentialId, String url, String username, String key, String password, Integer userId) {
        this.credentialId = credentialId;
        this.url = url;
        this.username = username;
        this.key = key;
        this.password = password;
        this.userId = userId;
    }

    public Integer getCredentialId() {
        return credentialId;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getKey() {
        return key;
    }

    public String getPassword() {
        return password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDecodedPassword() {
        return decodedPassword;
    }

    public void setDecodedPassword(String decodedPassword) {
        this.decodedPassword = decodedPassword;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
