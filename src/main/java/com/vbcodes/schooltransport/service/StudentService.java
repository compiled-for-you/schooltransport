package com.vbcodes.schooltransport.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vbcodes.schooltransport.dto.StudentDTO;
import com.vbcodes.schooltransport.dto.StudentParentFormDTO;
import com.vbcodes.schooltransport.entity.AppUser;
import com.vbcodes.schooltransport.entity.Organization;
import com.vbcodes.schooltransport.entity.Parent;
import com.vbcodes.schooltransport.entity.Student;
import com.vbcodes.schooltransport.repository.StudentRepository;

@Service
public class StudentService {
    private StudentRepository studentRepository;
    private ModelMapper modelMapper;

    @Autowired
    public StudentService(StudentRepository studentRepository, ModelMapper modelMapper){
        this.studentRepository=studentRepository;
        this.modelMapper=modelMapper;
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public List<Student> getAllStudentsFromOrganization(int orgId){
        return studentRepository.findByOrganizationId(orgId);
    }

    public Student getStudentByName(String studentName){
        return studentRepository.findByStudentName(studentName);
    }

    public List<Student> getStudentsByGrade(int grade){
        return studentRepository.findByGrade(grade);
    }

    public void saveNewStudent(StudentParentFormDTO studentParentFormDTO, Organization orgEntity, Parent parentEntity, AppUser appUser){
        Student studentEntity = mapFromDTOToEntity(studentParentFormDTO);
        studentEntity.setOrganization(orgEntity);
        studentEntity.setParent(parentEntity);
        studentEntity.setAppUser(appUser);
        studentRepository.save(studentEntity);
    }

    public StudentDTO mapFromEntityToDTO(Student studentEntity){
        return modelMapper.map(studentEntity, StudentDTO.class);
    }

    public Student mapFromDTOToEntity(StudentParentFormDTO studentParentFormDTO){
        return modelMapper.map(studentParentFormDTO, Student.class);
    }
}
