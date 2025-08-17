package com.vbcodes.schooltransport.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vbcodes.schooltransport.dto.StudentDTO;
import com.vbcodes.schooltransport.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<?> getAllStudents(){
        List<StudentDTO> students = studentService.getAllStudentsForCurrentUser();
        return ResponseEntity.status(HttpStatus.OK).body(students);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<?> updateStudent(@PathVariable int studentId, @RequestBody StudentDTO studentDTO){
        StudentDTO updatedStudent = studentService.updateStudent(studentId, studentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedStudent);
    }

    

}