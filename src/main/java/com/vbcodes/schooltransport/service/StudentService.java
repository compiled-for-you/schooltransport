package com.vbcodes.schooltransport.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vbcodes.schooltransport.dto.StudentDTO;
import com.vbcodes.schooltransport.dto.StudentParentFormDTO;
import com.vbcodes.schooltransport.entity.AppUser;
import com.vbcodes.schooltransport.entity.Organization;
import com.vbcodes.schooltransport.entity.Parent;
import com.vbcodes.schooltransport.entity.Student;
import com.vbcodes.schooltransport.exception.customexceptions.IllegalResourceAccessException;
import com.vbcodes.schooltransport.exception.customexceptions.ResourceNotFoundException;
import com.vbcodes.schooltransport.repository.StudentRepository;
import com.vbcodes.schooltransport.utils.CurrentUserUtil;

@Service
public class StudentService {
    private StudentRepository studentRepository;
    private OrgService orgService;
    private ParentService parentService;
    private AppUserService appUserService;
    private ModelMapper modelMapper;

    @Autowired
    public StudentService(StudentRepository studentRepository, OrgService orgService, ParentService parentService, AppUserService appUserService, ModelMapper modelMapper){
        this.studentRepository=studentRepository;
        this.orgService=orgService;
        this.parentService=parentService;
        this.appUserService=appUserService;
        this.modelMapper=modelMapper;
    }

    public List<StudentDTO> getAllStudentsForCurrentUser(){
        AppUser currentUser = CurrentUserUtil.getCurrentUser();
        String userType = currentUser.getRoles();

        List<Student> allStudentsList = new ArrayList<>();

        switch (userType) {
            case "ROLE_ORGANIZATION":
                allStudentsList = studentRepository.findByOrganizationAppUser(currentUser);
            break;
            case "ROLE_PARENT":
                allStudentsList = studentRepository.findByParentAppUser(currentUser);
            break;
            case "ROLE_DRIVER":
                // TODO : if driver, return all students assigned to the vehicle of this driver after checking the mappping table
            break;
            default:
                return Collections.emptyList();
            
        }

        return allStudentsList.stream()
                .map(this::mapFromEntityToDTO)
                .toList();
    }

    public Student getStudentByName(String studentName){
        return studentRepository.findByStudentName(studentName);
    }

    public List<Student> getStudentsByGrade(int grade){
        return studentRepository.findByGrade(grade);
    }

    @Transactional
    public void saveNewStudent(StudentParentFormDTO studentParentFormDTO){
        Organization orgEntity=orgService.getOrganizationById(studentParentFormDTO.getOrgId()).orElseThrow(() -> new ResourceNotFoundException("No Organization found with Org ID " + studentParentFormDTO.getOrgId()));

        AppUser studentAppUser = appUserService.saveAppUser(studentParentFormDTO);
        //TODO if user is not null and saved properly - 
        Parent parentEntity = parentService.saveNewParent(studentParentFormDTO, studentAppUser);
        //TODO if parent is saved properly and is not null - 

        Student studentEntity = mapFromDTOToEntity(studentParentFormDTO);
        studentEntity.setOrganization(orgEntity);
        studentEntity.setParent(parentEntity);
        studentEntity.setAppUser(studentAppUser);

        studentRepository.save(studentEntity);
    }

    @Transactional
    public StudentDTO updateStudent(int studentID, StudentDTO studentDTO){
        Student studentEntity = studentRepository.findById(studentID).orElseThrow(() -> new ResourceNotFoundException("No student found with ID : " + studentID));

        AppUser currentAppUser = CurrentUserUtil.getCurrentUser();
        Student currentStudent = studentRepository.findByAppUser(currentAppUser);

        if(studentEntity.getStudentId() != currentStudent.getStudentId()){
            throw new IllegalResourceAccessException("The student ID does not belong to the current user");
        }

        studentEntity.setStudentName(studentDTO.getStudentName());
        studentEntity.setAddress(studentDTO.getAddress());
        studentEntity.setGrade(studentDTO.getGrade());
        
        Student updatedStudent = studentRepository.save(studentEntity);
        return mapFromEntityToDTO(updatedStudent);
    }

    public StudentDTO mapFromEntityToDTO(Student studentEntity){
        return modelMapper.map(studentEntity, StudentDTO.class);
    }

    public Student mapFromDTOToEntity(StudentParentFormDTO studentParentFormDTO){
        return modelMapper.map(studentParentFormDTO, Student.class);
    }
}
