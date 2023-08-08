package com.vbcodes.schooltransport.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
        List<Student> list=  studentService.getAllStudents();
        System.out.println("THe number of students are " + list.size());
        for(Student s : list){
            System.out.println(s);
        }
        return list;
    }
}
