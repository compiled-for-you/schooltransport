package com.vbcodes.schooltransport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vbcodes.schooltransport.entity.Organization;

@Repository
public interface OrgRepository extends JpaRepository<Organization, Integer>{
    
}
