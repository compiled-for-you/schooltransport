package com.vbcodes.schooltransport.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vbcodes.schooltransport.dto.DriverVehicleMappingDTO;
import com.vbcodes.schooltransport.dto.StudentVehicleMappingDTO;
import com.vbcodes.schooltransport.entity.AppUser;
import com.vbcodes.schooltransport.entity.Driver;
import com.vbcodes.schooltransport.entity.DriverVehicleMapping;
import com.vbcodes.schooltransport.entity.Organization;
import com.vbcodes.schooltransport.entity.Student;
import com.vbcodes.schooltransport.entity.StudentVehicleMapping;
import com.vbcodes.schooltransport.entity.Vehicle;
import com.vbcodes.schooltransport.exception.customexceptions.IllegalResourceAccessException;
import com.vbcodes.schooltransport.exception.customexceptions.ResourceNotFoundException;
import com.vbcodes.schooltransport.exception.customexceptions.UnsupportedMappingOperationException;
import com.vbcodes.schooltransport.repository.DriverRepository;
import com.vbcodes.schooltransport.repository.DriverVehicleMappingRepository;
import com.vbcodes.schooltransport.repository.OrgRepository;
import com.vbcodes.schooltransport.repository.StudentRepository;
import com.vbcodes.schooltransport.repository.StudentVehicleMappingRepository;
import com.vbcodes.schooltransport.repository.VehicleRepository;
import com.vbcodes.schooltransport.utils.CurrentUserUtil;

@Service
public class MappingService {

    private DriverVehicleMappingRepository dvmRepository;
    private StudentVehicleMappingRepository svmRepository;
    private OrgRepository orgRepository;
    private DriverRepository driverRepository;
    private VehicleRepository vehicleRepository;
    private StudentRepository studentRepository;

    @Autowired
    public MappingService(DriverVehicleMappingRepository dvmRepository, StudentVehicleMappingRepository svmRepository, OrgRepository orgRepository, DriverRepository driverRepository, VehicleRepository vehicleRepository, StudentRepository studentRepository){
        this.dvmRepository=dvmRepository;
        this.svmRepository=svmRepository;
        this.orgRepository=orgRepository;
        this.driverRepository=driverRepository;
        this.vehicleRepository=vehicleRepository;
        this.studentRepository=studentRepository;
    }

    public List<DriverVehicleMappingDTO> getDriverVehicleMappings(){
        AppUser currentAppUser = CurrentUserUtil.getCurrentUser();
        Organization currentOrganization = orgRepository.findOrganizationByAppUser(currentAppUser);

        List<DriverVehicleMapping> dvmList = dvmRepository.findByOrganization_OrgIdAndIsActive(currentOrganization.getOrgId(), true);
        return dvmList.stream()
                        .map(mapping -> new DriverVehicleMappingDTO(
                            mapping.getDriver().getDriverId(), 
                            mapping.getVehicle().getVehicleId()))
                        .collect(Collectors.toList());
    }

    @Transactional
    public void mapDriverToVehicle(DriverVehicleMappingDTO driverVehicleMappingDTO) {
        AppUser currentAppUser = CurrentUserUtil.getCurrentUser();

        Organization currentOrganization = orgRepository.findOrganizationByAppUser(currentAppUser);
        if(!driverRepository.existsByDriverIdAndOrganization_OrgId(driverVehicleMappingDTO.getDriverID(), currentOrganization.getOrgId())){
            throw new IllegalResourceAccessException("The driver being mapped does not belong to the current organization.");
        }
        if(!vehicleRepository.existsByVehicleIdAndOrganization_OrgId(driverVehicleMappingDTO.getVehicleID(), currentOrganization.getOrgId())){
            throw new IllegalResourceAccessException("The vehicle being mapped does not belong to the current organization.");
        }
        //TODO : create a method to check if the current Driver already has an active mapping

        DriverVehicleMapping driverVehicleMapping = new DriverVehicleMapping();
        driverVehicleMapping.setDriver(new Driver(driverVehicleMappingDTO.getDriverID()));     //passing just the driverID so that entire driver is not needed to be fetched
        driverVehicleMapping.setVehicle(new Vehicle(driverVehicleMappingDTO.getVehicleID()));  //passing just the vehicleID so that entire vehicle is not needed to be fetched 
        driverVehicleMapping.setOrganization(currentOrganization);                             //passing entire org bcs it is already fetched

        dvmRepository.save(driverVehicleMapping);
    }

    public void deMapDriverFromVehicle(Integer driverID, Integer vehicleID){
        // Step 1: Does the mapping exist?
        DriverVehicleMapping mapping = dvmRepository.findByDriver_DriverIdAndVehicle_VehicleId(driverID, vehicleID)
            .orElseThrow(() -> new ResourceNotFoundException("No mapping exists between given driver and vehicle"));

        
        // Step 2: Does it belong to current org?
        AppUser currentAppUser = CurrentUserUtil.getCurrentUser();
        Organization currentOrganization = orgRepository.findOrganizationByAppUser(currentAppUser);
        if (!mapping.getOrganization().getOrgId().equals(currentOrganization.getOrgId())) {
            throw new IllegalResourceAccessException("You do not own this mapping.");
        }

        // Step 3: Is it already inactive?
        if (!mapping.isActive()) {
            throw new UnsupportedMappingOperationException("Mapping is already inactive.");
        }

        // Step 4: Soft delete
        mapping.setActive(false);
        dvmRepository.save(mapping);
    }

    public List<StudentVehicleMappingDTO> getStudentVehicleMappings(){
        AppUser currentAppUser = CurrentUserUtil.getCurrentUser();
        Organization currOrganization = orgRepository.findOrganizationByAppUser(currentAppUser);

        List<StudentVehicleMapping> svmList = svmRepository.findByOrganization_OrgIdAndIsActive(currOrganization.getOrgId(), true);

        return svmList.stream()
                        .map(mapping -> new StudentVehicleMappingDTO(
                            mapping.getStudent().getStudentId(),
                            mapping.getVehicle().getVehicleId()
                        ))
                        .collect(Collectors.toList());
    }

    @Transactional
    public void mapStudentToVehicle(StudentVehicleMappingDTO studentVehicleMappingDTO) {
        AppUser currentAppUser = CurrentUserUtil.getCurrentUser();

        Organization currentOrganization = orgRepository.findOrganizationByAppUser(currentAppUser);
        if(!studentRepository.existsByStudentIdAndOrganization_OrgId(studentVehicleMappingDTO.getStudentID(), currentOrganization.getOrgId())){
            throw new IllegalResourceAccessException("The student being mapped does not belong to the current organization.");
        }
        if(!vehicleRepository.existsByVehicleIdAndOrganization_OrgId(studentVehicleMappingDTO.getVehicleID(), currentOrganization.getOrgId())){
            throw new IllegalResourceAccessException("The vehicle being mapped does not belong to the current organization.");
        }
        //TODO : create a method to check if the current student already has an active mapping

        StudentVehicleMapping studentVehicleMapping = new StudentVehicleMapping();
        studentVehicleMapping.setStudent(new Student(studentVehicleMappingDTO.getStudentID()));
        studentVehicleMapping.setVehicle(new Vehicle(studentVehicleMappingDTO.getVehicleID()));
        studentVehicleMapping.setOrganization(currentOrganization);

        svmRepository.save(studentVehicleMapping);
    }

    public void deMapStudentFromVehicle(Integer studentID, Integer vehicleID){
        // Step 1: Does the mapping exist?
        StudentVehicleMapping mapping = svmRepository.findByStudent_StudentIdAndVehicle_VehicleId(studentID, vehicleID)
            .orElseThrow(() -> new ResourceNotFoundException("No mapping exists between given student and vehicle"));

        
        // Step 2: Does it belong to current org?
        AppUser currentAppUser = CurrentUserUtil.getCurrentUser();
        Organization currentOrganization = orgRepository.findOrganizationByAppUser(currentAppUser);
        if (!mapping.getOrganization().getOrgId().equals(currentOrganization.getOrgId())) {
            throw new IllegalResourceAccessException("You do not own this mapping.");
        }

        // Step 3: Is it already inactive?
        if (!mapping.isActive()) {
            throw new UnsupportedMappingOperationException("Mapping is already inactive.");
        }

        // Step 4: Soft delete
        mapping.setActive(false);
        svmRepository.save(mapping);
    }
    
}
