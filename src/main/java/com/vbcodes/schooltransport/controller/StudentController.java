package com.vbcodes.schooltransport.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vbcodes.schooltransport.entity.Organization;
import com.vbcodes.schooltransport.entity.Student;
import com.vbcodes.schooltransport.service.OrgService;
import com.vbcodes.schooltransport.service.StudentService;

@RestController
public class StudentController {
    private StudentService studentService;
    private OrgService orgService;

    @Autowired
    public StudentController(StudentService studentService, OrgService orgService){
        this.studentService = studentService;
        this.orgService = orgService;
    }

    @GetMapping("/students")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/organization/students")
    public List<Student> getAllStudentsFromOrganization(Authentication auth){
        Organization currentOrganization = orgService.getCurrentLoggedInOrganization(auth);
        System.out.println(currentOrganization);
        if(currentOrganization==null)
            return null;
        else
            return studentService.getAllStudentsFromOrganization(currentOrganization.getOrgId());
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