package com.vbcodes.schooltransport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vbcodes.schooltransport.entity.Organization;

@Repository
public interface OrgRepository extends JpaRepository<Organization, Integer>{
    @Query(value="select * from organizations where user_id=?1", nativeQuery = true)
        Organization findOrganizationByUserId(Integer userId);
}
