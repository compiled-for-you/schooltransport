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
import com.vbcodes.schooltransport.entity.AppUser;
import com.vbcodes.schooltransport.entity.Organization;
import com.vbcodes.schooltransport.entity.Parent;
import com.vbcodes.schooltransport.responses.ErrorResponse;
import com.vbcodes.schooltransport.responses.SuccessResponse;
import com.vbcodes.schooltransport.service.AppUserService;
import com.vbcodes.schooltransport.service.DriverService;
import com.vbcodes.schooltransport.service.OrgService;
import com.vbcodes.schooltransport.service.ParentService;
import com.vbcodes.schooltransport.service.StudentService;

@RestController
@RequestMapping("/register")
public class RegisterController {

    private OrgService orgService;
    private DriverService driverService;
    private ParentService parentService;
    private StudentService studentService;
    private AppUserService appUserService;

    @Autowired
    RegisterController(OrgService orgService, DriverService driverService, ParentService parentService, StudentService studentService, AppUserService appUserService){
        this.orgService=orgService;
        this.driverService=driverService;
        this.parentService=parentService;
        this.studentService=studentService;
        this.appUserService=appUserService;
    }

    @PostMapping("/organization")
    private ResponseEntity<Object> registerOrganization(@RequestBody OrganizationDTO orgDTO){
        System.out.println(orgDTO);
        try{
            AppUser user = appUserService.saveAppUser(orgDTO);
            orgService.saveNewOrganization(orgDTO, user);
            SuccessResponse successResponse = new SuccessResponse("Organization Registration Successful", "/login");
            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        }
        catch(Exception e){
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), null);
            return new ResponseEntity<>(errorResponse , HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/driver")
    private ResponseEntity<Object> registerDriver(@RequestBody DriverDTO driverDTO){
        System.out.println(driverDTO);
        try{
            Organization orgEntity=orgService.getOrganizationById(driverDTO.getOrgId()).orElse(null);
            if(orgEntity!=null){
                AppUser user = appUserService.saveAppUser(driverDTO);
                //TODO if user is not null and saved properly - 
                driverService.saveNewDriver(driverDTO, orgEntity, user);
                SuccessResponse successResponse = new SuccessResponse("Driver Registration Successful", "/login");
                return new ResponseEntity<>(successResponse, HttpStatus.OK);
            }
            else {
                ErrorResponse errorResponse = new ErrorResponse("No Organization with given id found", null);
                return new ResponseEntity<>(errorResponse , HttpStatus.BAD_REQUEST);
            }
        }
        catch(Exception e){
            ErrorResponse errorResponse = new ErrorResponse("Some error occurred while saving driver data!", null);
            return new ResponseEntity<>(errorResponse , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/student")
    private ResponseEntity<Object> registerStudent(@RequestBody StudentParentFormDTO studentParentFormDTO){
        System.out.println(studentParentFormDTO);
        try{
            Organization orgEntity=orgService.getOrganizationById(studentParentFormDTO.getOrgId()).orElse(null);
            if(orgEntity!=null){
                AppUser user = appUserService.saveAppUser(studentParentFormDTO);
                //TODO if user is not null and saved properly - 
                Parent parentEntity = parentService.saveNewParent(studentParentFormDTO, user);
                //TODO if parent is saved properly and is not null - 
                studentService.saveNewStudent(studentParentFormDTO, orgEntity, parentEntity, user);
                SuccessResponse successResponse = new SuccessResponse("Student Registration Successful", "/login");
                return new ResponseEntity<>(successResponse, HttpStatus.OK);
            }
            else{
                System.out.println("No organization with id "+studentParentFormDTO.getOrgId()+" is found");
                ErrorResponse errorResponse = new ErrorResponse("No organization with id "+studentParentFormDTO.getOrgId()+" is found", null);
                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
            }
        }
        catch(Exception e){
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}