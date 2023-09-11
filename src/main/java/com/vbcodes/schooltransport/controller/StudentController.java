package com.vbcodes.schooltransport.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vbcodes.schooltransport.entity.Student;
import com.vbcodes.schooltransport.service.StudentService;

@RestController
public class StudentController {
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();  
    }

    @GetMapping("/organizations/{orgId}/students")
    public List<Student> getAllStudentsFromOrganization(@PathVariable int orgId){
        return studentService.getAllStudentsFromOrganization(orgId);
    }

    @GetMapping("student/name/{studentName}")
    public Student getStudentByName(@PathVariable String studentName){
        return studentService.getStudentByName(studentName);
    }

    @GetMapping("students/grade/{grade}")
    public List<Student> getStudentsByGrade(@PathVariable int grade){
        return studentService.getStudentsByGrade(grade);
    }
}