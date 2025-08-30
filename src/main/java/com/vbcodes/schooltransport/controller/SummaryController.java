package com.vbcodes.schooltransport.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vbcodes.schooltransport.dto.card_dto.BaseCardDTO;
import com.vbcodes.schooltransport.service.SummaryService;

@RestController
public class SummaryController {
    
    Logger logger = Logger.getLogger(SummaryController.class.getName());

    private SummaryService summaryService;

    @Autowired
    public SummaryController(SummaryService summaryService){
        this.summaryService=summaryService;
    }

    @GetMapping("/summary")
    public ResponseEntity<?> getSummary(){
        List<BaseCardDTO> summaryCardsList = summaryService.getSummaryCards();
        return ResponseEntity.status(HttpStatus.OK).body(summaryCardsList);
    }
}
