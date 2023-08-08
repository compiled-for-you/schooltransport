package com.vbcodes.schooltransport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vbcodes.schooltransport.entity.Parent;
import com.vbcodes.schooltransport.repository.ParentRepository;

@Service
public class ParentService {
    private ParentRepository parentRepository;

    @Autowired
    public ParentService(ParentRepository parentRepository){
        this.parentRepository=parentRepository;
    }

    public List<Parent> getAllParents(){
        return parentRepository.findAll();
    }
}
