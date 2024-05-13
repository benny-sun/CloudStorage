package com.udacity.jwdnd.c1.cloudstorage.controllers;

import com.udacity.jwdnd.c1.cloudstorage.models.Credential;
import com.udacity.jwdnd.c1.cloudstorage.models.User;
import com.udacity.jwdnd.c1.cloudstorage.services.CredentialService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/credentials")
public class CredentialController {

    private final CredentialService credentialService;

    public CredentialController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PostMapping
    public String create(
        @ModelAttribute Credential credential,
        Authentication auth,
        Model model
    ) {
        String errorMessage = null;

        Integer userId = ((User) auth.getPrincipal()).getUserId();
        credential.setUserId(userId);
        int addedRows = credentialService.create(credential);

        if (addedRows < 1) {
            errorMessage = "Something went wrong. Please try again.";
        }

        model.addAttribute("anchor", "nav-credentials");
        model.addAttribute("errorMessage", errorMessage);

        return "result";
    }

}
