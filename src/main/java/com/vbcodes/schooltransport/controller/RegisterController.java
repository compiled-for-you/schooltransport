package com.vbcodes.schooltransport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vbcodes.schooltransport.dto.DriverDTO;
import com.vbcodes.schooltransport.dto.OrganizationDTO;
import com.vbcodes.schooltransport.dto.StudentParentFormDTO;
import com.vbcodes.schooltransport.responses.SuccessResponse;
import com.vbcodes.schooltransport.service.DriverService;
import com.vbcodes.schooltransport.service.OrgService;
import com.vbcodes.schooltransport.service.StudentService;

@RestController
@RequestMapping("/register")
public class RegisterController {

    private OrgService orgService;
    private DriverService driverService;
    private StudentService studentService;

    @Autowired
    RegisterController(OrgService orgService, DriverService driverService, StudentService studentService){
        this.orgService=orgService;
        this.driverService=driverService;
        this.studentService=studentService;
    }

    @PostMapping("/organization")
    private ResponseEntity<Object> registerOrganization(@RequestBody OrganizationDTO orgDTO){
        orgService.saveNewOrganization(orgDTO);
        //keeping this success response for frontend as of now might delete later
        SuccessResponse successResponse = new SuccessResponse("Organization Registration Successful", "/login");
        return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
    }

    @PostMapping("/driver")
    private ResponseEntity<Object> registerDriver(@RequestBody DriverDTO driverDTO){
        driverService.saveNewDriver(driverDTO);
        //keeping this success response for frontend as of now might delete later
        SuccessResponse successResponse = new SuccessResponse("Driver Registration Successful", "/login");
        return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
    }

    @PostMapping("/student")
    private ResponseEntity<Object> registerStudent(@RequestBody StudentParentFormDTO studentParentFormDTO){
        studentService.saveNewStudent(studentParentFormDTO);
        //keeping this success response for frontend as of now might delete later
        SuccessResponse successResponse = new SuccessResponse("Student Registration Successful", "/login");
        return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
    }
}