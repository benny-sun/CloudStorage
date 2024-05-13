package com.udacity.jwdnd.c1.cloudstorage.services;

import com.udacity.jwdnd.c1.cloudstorage.models.Credential;
import com.udacity.jwdnd.c1.cloudstorage.orm.CredentialMapper;
import org.springframework.stereotype.Service;

@Service
public class CredentialService {

    private final EncryptionService encryptionService;
    private final KeyService keyService;
    private final CredentialMapper credentialMapper;

    public CredentialService(EncryptionService encryptionService, KeyService keyService, CredentialMapper credentialMapper) {
        this.encryptionService = encryptionService;
        this.keyService = keyService;
        this.credentialMapper = credentialMapper;
    }

    public int create(Credential credential) {
        String secureKey = keyService.generateSecureKey();
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), secureKey);

        return credentialMapper.insert(new Credential(
                null,
                credential.getUrl(),
                credential.getUsername(),
                secureKey,
                encryptedPassword,
                credential.getUserId()
        ));
    }

}
