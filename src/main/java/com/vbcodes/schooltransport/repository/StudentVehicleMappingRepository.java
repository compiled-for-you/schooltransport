package com.vbcodes.schooltransport.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vbcodes.schooltransport.entity.StudentVehicleMapping;

@Repository
public interface StudentVehicleMappingRepository extends JpaRepository<StudentVehicleMapping, Long>{

    List<StudentVehicleMapping> findByOrganization_OrgIdAndIsActive(Integer orgId, boolean isActive);

    Optional<StudentVehicleMapping> findByStudent_StudentIdAndVehicle_VehicleId(Integer studentID, Integer vehicleID);

}
