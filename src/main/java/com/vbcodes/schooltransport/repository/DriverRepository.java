package com.vbcodes.schooltransport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vbcodes.schooltransport.entity.AppUser;
import com.vbcodes.schooltransport.entity.Driver;
import com.vbcodes.schooltransport.projection.DriverCardProjection;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {

    Driver findDriverByAppUser(AppUser appUser);

    @Query(value = "select * from drivers where user_id=?1", nativeQuery = true)
    Driver findDriverByUserId(Integer userId);

    List<Driver> findByOrganizationAppUser(AppUser currentUser);

    boolean existsByDriverIdAndOrganization_OrgId(Integer driverID, Integer organizationID);

    @Query(value = 
    """
        SELECT
            COUNT(*) AS totalDrivers,
            SUM(
                CASE WHEN EXISTS (
                    SELECT 1
                    FROM driver_vehicle_mappings dvm
                    WHERE dvm.driver_id = d.driver_id
                    AND dvm.is_active = 1 
                ) THEN 0 ELSE 1 END
            ) AS unmappedDrivers,
            0 AS driversInCommute
        FROM drivers d
        WHERE d.organization_id = :orgId
    """, nativeQuery=true)
    DriverCardProjection getDriverCardDetailsForOrganization(Integer orgId);
}