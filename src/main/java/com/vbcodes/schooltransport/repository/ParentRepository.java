package com.vbcodes.schooltransport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vbcodes.schooltransport.entity.Parent;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Integer>{
    
}
