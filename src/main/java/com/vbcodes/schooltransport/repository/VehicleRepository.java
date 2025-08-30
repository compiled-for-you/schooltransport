package com.vbcodes.schooltransport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vbcodes.schooltransport.entity.AppUser;
import com.vbcodes.schooltransport.entity.Vehicle;
import com.vbcodes.schooltransport.projection.VehicleCardProjection;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer>{
    
    List<Vehicle> findByOrganizationOrgId(int orgId);

    List<Vehicle> findByOrganizationAppUser(AppUser appUser);

    boolean existsByVehicleIdAndOrganization_OrgId(Integer vehicleID, Integer organizationID);

    @Query(value = """
        SELECT
            COUNT(*) AS totalVehicles,
            SUM(
                CASE WHEN EXISTS(
                    SELECT 1
                    FROM driver_vehicle_mappings dvm
                    JOIN student_vehicle_mappings svm ON dvm.vehicle_id = svm.vehicle_id
                    WHERE dvm.vehicle_id = v.vehicle_id
                    AND dvm.is_active = 1
                    AND svm.is_active = 1
                ) THEN 0 ELSE 1 END
            ) AS unmappedVehicles,
            3 AS vehiclesInCommute
        FROM vehicles v
        WHERE v.organization_id = :orgId
    """, nativeQuery = true)
    public VehicleCardProjection getVehicleCardDetailsForOrganization(Integer orgId);
}
