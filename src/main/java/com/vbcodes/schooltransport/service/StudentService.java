package com.vbcodes.schooltransport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vbcodes.schooltransport.entity.Student;
import com.vbcodes.schooltransport.repository.StudentRepository;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
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
}
