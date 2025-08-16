package com.vbcodes.schooltransport.controller;

import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vbcodes.schooltransport.configuration.CustomAppUser;

@RestController
public class SummaryController {
    
    Logger logger = Logger.getLogger(SummaryController.class.getName());

    @GetMapping("/summary")
    public ResponseEntity<?> getSummary(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomAppUser user = (CustomAppUser) auth.getPrincipal();
        String username = user.getUsername();

        logger.info("User " + username + " has accessed the summary endpoint.");

        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
