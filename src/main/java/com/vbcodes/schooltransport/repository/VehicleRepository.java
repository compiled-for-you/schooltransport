package com.vbcodes.schooltransport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vbcodes.schooltransport.entity.AppUser;
import com.vbcodes.schooltransport.entity.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer>{
    
    List<Vehicle> findByOrganizationOrgId(int orgId);

    List<Vehicle> findByOrganizationAppUser(AppUser appUser);
}
