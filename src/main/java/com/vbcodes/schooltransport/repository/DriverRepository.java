package com.vbcodes.schooltransport.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vbcodes.schooltransport.entity.Driver;


public interface DriverRepository extends JpaRepository<Driver, Integer>{
    
}