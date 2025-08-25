package com.vbcodes.schooltransport.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vbcodes.schooltransport.entity.DriverVehicleMapping;

@Repository
public interface DriverVehicleMappingRepository extends JpaRepository<DriverVehicleMapping, Long>{
    boolean existsByDriver_DriverIdAndIsActiveTrue(Integer driverId);

    List<DriverVehicleMapping> findByOrganization_OrgIdAndIsActive(Integer orgId, boolean isActive);

    Optional<DriverVehicleMapping> findByDriver_DriverIdAndVehicle_VehicleId(Integer driverID, Integer vehicleID);
}
