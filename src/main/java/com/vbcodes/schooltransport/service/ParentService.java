package com.vbcodes.schooltransport.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vbcodes.schooltransport.dto.ParentDTO;
import com.vbcodes.schooltransport.dto.StudentParentFormDTO;
import com.vbcodes.schooltransport.entity.AppUser;
import com.vbcodes.schooltransport.entity.Parent;
import com.vbcodes.schooltransport.repository.ParentRepository;

@Service
public class ParentService {
    private ParentRepository parentRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ParentService(ParentRepository parentRepository, ModelMapper modelMapper){
        this.parentRepository=parentRepository;
        this.modelMapper=modelMapper;
    }

    public List<Parent> getAllParents(){
        return parentRepository.findAll();
    }

    @Transactional
    public Parent saveNewParent(StudentParentFormDTO studentParentFormDTO, AppUser appUser){
        Parent parentEntity = mapFromDTOToEntity(studentParentFormDTO);
        parentEntity.setAppUser(appUser); 
        return parentRepository.save(parentEntity);
    }

    public ParentDTO mapFromEntityToDTO(Parent parentEntity){
        return modelMapper.map(parentEntity, ParentDTO.class);
    }

    public Parent mapFromDTOToEntity(StudentParentFormDTO studentParentFormDTO){
        return modelMapper.map(studentParentFormDTO, Parent.class);
    }
}
