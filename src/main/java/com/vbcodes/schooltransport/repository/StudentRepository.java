package com.vbcodes.schooltransport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.vbcodes.schooltransport.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

    @Query(value="select * from students where organization_id=?1", nativeQuery = true)
    public List<Student> findByOrganizationId(int organization_id); 
    
    Student findByStudentName(String studentName);

    List<Student> findByGrade(int grade);
}
