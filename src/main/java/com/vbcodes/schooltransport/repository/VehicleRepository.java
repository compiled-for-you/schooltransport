package com.vbcodes.schooltransport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vbcodes.schooltransport.entity.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer>{
    List<Vehicle> findByOrganizationOrgId(int orgId);

    @Query(value="SELECT vehicle_id FROM drivers WHERE driver_id=?1", nativeQuery = true)
    List<Vehicle> findByDriver(Integer driverId);
}
