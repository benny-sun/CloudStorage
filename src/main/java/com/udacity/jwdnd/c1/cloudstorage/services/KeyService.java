package com.udacity.jwdnd.c1.cloudstorage.services;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class KeyService {
    public String generateSecureKey() {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);

        return Base64.getEncoder().encodeToString(key);
    }
}
