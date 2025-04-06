package com.vbcodes.schooltransport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vbcodes.schooltransport.entity.Driver;


public interface DriverRepository extends JpaRepository<Driver, Integer>{
    @Query(value="select * from drivers where user_id=?1", nativeQuery = true)
        Driver findDriverByUserId(Integer userId);
}