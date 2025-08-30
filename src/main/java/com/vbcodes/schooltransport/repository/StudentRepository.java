package com.vbcodes.schooltransport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vbcodes.schooltransport.entity.AppUser;
import com.vbcodes.schooltransport.entity.Student;
import com.vbcodes.schooltransport.projection.StudentCardProjection;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

    @Query(value="select * from students where organization_id=?1", nativeQuery = true)
    public List<Student> findByOrganizationId(int organization_id); 
    
    List<Student> findByOrganizationAppUser(AppUser appUser);

    List<Student> findByParentAppUser(AppUser appUser);

    Student findByStudentName(String studentName);

    Student findByAppUser(AppUser appUser);

    List<Student> findByGrade(int grade);

    boolean existsByStudentIdAndOrganization_OrgId(Integer studentID, Integer orgID);

    @Query(value = """
        SELECT 
            COUNT(*) AS totalStudents,
            SUM(
                CASE WHEN EXISTS(
                    SELECT 1
                    FROM student_vehicle_mappings svm
                    WHERE svm.student_id=s.student_id 
                    AND svm.is_active = 1
                )
                THEN 0 ELSE 1 END
            ) AS unmappedStudents
        FROM students s
        WHERE s.organization_id = :orgId
    """, nativeQuery = true)
    StudentCardProjection getStudentCardDetailsForOrganization(Integer orgId);
}
