package com.vbcodes.schooltransport.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vbcodes.schooltransport.dto.VehicleDTO;
import com.vbcodes.schooltransport.entity.Vehicle;
import com.vbcodes.schooltransport.responses.ErrorResponse;
import com.vbcodes.schooltransport.responses.SuccessResponse;
import com.vbcodes.schooltransport.service.VehicleService;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private static final Logger logger = LoggerFactory.getLogger(VehicleController.class);

    private VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllVehicles() {
        List<Vehicle> allVehicles = vehicleService.getAllVehicles();
        if (allVehicles == null || allVehicles.isEmpty()) {
            return new ResponseEntity<>(new ErrorResponse("No vehicle details found !", null), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(allVehicles, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewVehicle(@RequestBody VehicleDTO vehicleDTO) {
        try {
            vehicleService.addNewVehicle(vehicleDTO);
            return new ResponseEntity<>(new SuccessResponse("Vehicle added successfully", null), HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error while adding vehicle: " + e.getMessage());
            return new ResponseEntity<>(new ErrorResponse("Error occurred while adding vehicle", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{vehicleID}/update")
    public ResponseEntity<?> updateVehicle(@PathVariable int vehicleID, @RequestBody VehicleDTO vehicleDTO) {
        try {
            vehicleService.updateVehicle(vehicleID, vehicleDTO);
            return new ResponseEntity<>(new SuccessResponse("Vehicle updated successfully", null), HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error while updating vehicle: " + e.getMessage());
            return new ResponseEntity<>(new ErrorResponse("Error occurred while updating vehicle", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}