package com.vbcodes.schooltransport.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vbcodes.schooltransport.entity.StudentVehicleMapping;
import com.vbcodes.schooltransport.projection.MyDriverProjection;
import com.vbcodes.schooltransport.projection.MyVehicleProjection;

@Repository
public interface StudentVehicleMappingRepository extends JpaRepository<StudentVehicleMapping, Long>{

    List<StudentVehicleMapping> findByOrganization_OrgIdAndIsActive(Integer orgId, boolean isActive);

    Optional<StudentVehicleMapping> findByStudent_StudentIdAndVehicle_VehicleId(Integer studentID, Integer vehicleID);

    @Query(value = """
    SELECT 
        v.vehicle_registration_number AS vehicleRegistrationNumber,
        v.vehicle_number AS vehicleNumber,
        v.vehicle_type AS vehicleType
    FROM vehicles v 
    JOIN student_vehicle_mappings svm ON v.vehicle_id=svm.vehicle_id
    WHERE svm.student_id= :studentId AND svm.is_active=1;
    """, nativeQuery = true)
    MyVehicleProjection getVehicleCardDetailsForStudent(Integer studentId);

    @Query(value = """
    SELECT 
        d.full_name AS driverName,
        d.contact_number AS driverContactNumber
    FROM drivers d
    JOIN driver_vehicle_mappings dvm 
        ON d.driver_id = dvm.driver_id AND dvm.is_active = 1
    JOIN student_vehicle_mappings svm 
        ON dvm.vehicle_id = svm.vehicle_id AND svm.is_active = 1
    WHERE svm.student_id = :studentId
    """, nativeQuery = true)
    MyDriverProjection findDriverCardDetailsForStudent(Integer studentId);

}
