package com.vbcodes.schooltransport.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vbcodes.schooltransport.entity.DriverVehicleMapping;
import com.vbcodes.schooltransport.projection.MyVehicleProjection;

@Repository
public interface DriverVehicleMappingRepository extends JpaRepository<DriverVehicleMapping, Long>{
    boolean existsByDriver_DriverIdAndIsActiveTrue(Integer driverId);

    List<DriverVehicleMapping> findByOrganization_OrgIdAndIsActive(Integer orgId, boolean isActive);

    Optional<DriverVehicleMapping> findByDriver_DriverIdAndVehicle_VehicleId(Integer driverID, Integer vehicleID);

    @Query(value = """
        SELECT 
            v.vehicle_registration_number AS vehicleRegistrationNumber,
            v.vehicle_number AS vehicleNumber,
            v.vehicle_type AS vehicleType
            FROM vehicles v 
            JOIN driver_vehicle_mappings dvm ON v.vehicle_id=dvm.vehicle_id
            WHERE dvm.driver_id= :driverId AND dvm.is_active=1;
    """, nativeQuery = true)
    MyVehicleProjection getVehicleCardDetailsForDriver(Integer driverId);
}
