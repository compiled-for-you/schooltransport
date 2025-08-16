package com.vbcodes.schooltransport.controller;

import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vbcodes.schooltransport.entity.AppUser;
import com.vbcodes.schooltransport.utils.CurrentUserUtil;

@RestController
public class SummaryController {
    
    Logger logger = Logger.getLogger(SummaryController.class.getName());

    @GetMapping("/summary")
    public ResponseEntity<?> getSummary(){
        
        AppUser currentUser = CurrentUserUtil.getCurrentUser();
        if (currentUser != null) {
            
        }

        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
