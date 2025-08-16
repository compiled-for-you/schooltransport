package com.vbcodes.schooltransport.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vbcodes.schooltransport.dto.VehicleDTO;
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

    @GetMapping
    public ResponseEntity<?> getAllVehicles() {
        List<VehicleDTO> allVehicles = vehicleService.getAllVehiclesForCurrentUser();
        return ResponseEntity.status(HttpStatus.OK).body(allVehicles);
    }

    @PostMapping
    public ResponseEntity<?> addNewVehicle(@RequestBody VehicleDTO vehicleDTO) {
        VehicleDTO createdVehicle = vehicleService.addNewVehicle(vehicleDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVehicle);
    }

    @PutMapping("/{vehicleID}")
    public ResponseEntity<?> updateVehicle(@PathVariable int vehicleID, @RequestBody VehicleDTO vehicleDTO) {
        VehicleDTO updatedVehicle = vehicleService.updateVehicle(vehicleID, vehicleDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedVehicle);
    }

    @DeleteMapping("/{vehicleID}")
    public ResponseEntity<?> deleteVehicle(@PathVariable int vehicleID) {
        vehicleService.deleteVehicle(vehicleID);
        return ResponseEntity.noContent().build();
    }

}