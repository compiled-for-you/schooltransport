package com.vbcodes.schooltransport.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vbcodes.schooltransport.entity.Parent;
import com.vbcodes.schooltransport.service.ParentService;

@RestController
@RequestMapping("/parents")
public class ParentController {
    private ParentService parentService;

    @Autowired
    public ParentController(ParentService parentService){
        this.parentService=parentService;
    }

    @GetMapping("/all")
    public List<Parent> getAllParents(){
        return parentService.getAllParents();
    }
}