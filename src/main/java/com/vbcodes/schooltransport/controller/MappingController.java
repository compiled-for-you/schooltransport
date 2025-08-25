package com.vbcodes.schooltransport.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vbcodes.schooltransport.dto.DriverVehicleMappingDTO;
import com.vbcodes.schooltransport.dto.StudentVehicleMappingDTO;
import com.vbcodes.schooltransport.service.MappingService;

@RestController
@RequestMapping("/mapping")
public class MappingController {
    
    private MappingService mappingService;

    @Autowired
    public MappingController(MappingService mappingService){
        this.mappingService=mappingService;
    }

    @GetMapping("/driver-vehicle")
    public ResponseEntity<?> getDriverVehicleMappings(){
        List<DriverVehicleMappingDTO> dvmList = mappingService.getDriverVehicleMappings();
        return ResponseEntity.status(HttpStatus.OK).body(dvmList);
    }

    @PostMapping("/driver-vehicle")
    public ResponseEntity<?> mapDriverToVehicle(@RequestBody DriverVehicleMappingDTO driverVehicleMappingDTO){
        mappingService.mapDriverToVehicle(driverVehicleMappingDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/driver-vehicle/{driverID}-{vehicleID}")
    public ResponseEntity<?> deleteDriverVehicleMapping(@PathVariable int driverID, @PathVariable int vehicleID){
        mappingService.deMapDriverFromVehicle(driverID, vehicleID);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/student-vehicle")
    public ResponseEntity<?> getStudentVehicleMappings(){
        List<StudentVehicleMappingDTO> svmList = mappingService.getStudentVehicleMappings();
        return ResponseEntity.status(HttpStatus.OK).body(svmList);
    }

    @PostMapping("/student-vehicle")
    public ResponseEntity<?> mapDriverToVehicle(@RequestBody StudentVehicleMappingDTO studentVehicleMappingDTO){
        mappingService.mapStudentToVehicle(studentVehicleMappingDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/student-vehicle/{studentID}-{vehicleID}")
    public ResponseEntity<?> deleteStudentVehicleMapping(@PathVariable Integer studentID, @PathVariable Integer vehicleID){
        mappingService.deMapStudentFromVehicle(studentID, vehicleID);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    
}
