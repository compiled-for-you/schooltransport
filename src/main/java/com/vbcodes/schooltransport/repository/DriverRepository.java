package com.vbcodes.schooltransport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vbcodes.schooltransport.entity.AppUser;
import com.vbcodes.schooltransport.entity.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer>{

    Driver findDriverByAppUser(AppUser appUser);

    @Query(value="select * from drivers where user_id=?1", nativeQuery = true)
    Driver findDriverByUserId(Integer userId);

    List<Driver> findByOrganizationAppUser(AppUser currentUser);

    boolean existsByDriverIdAndOrganization_OrgId(Integer driverID, Integer organizationID);
}