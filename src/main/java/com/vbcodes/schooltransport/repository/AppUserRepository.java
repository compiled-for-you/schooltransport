package com.vbcodes.schooltransport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vbcodes.schooltransport.entity.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer>{
    AppUser findByUsername(String username);

    @Query(value="select user_id from app_users where username=?1", nativeQuery=true)
    Integer findUserIdByUsername(String username);
}