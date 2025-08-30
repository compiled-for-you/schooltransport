package com.vbcodes.schooltransport.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vbcodes.schooltransport.dto.card_dto.BaseCardDTO;
import com.vbcodes.schooltransport.dto.card_dto.DriverCardDTO;
import com.vbcodes.schooltransport.dto.card_dto.MappingCardDTO;
import com.vbcodes.schooltransport.dto.card_dto.MyDriverCardDTO;
import com.vbcodes.schooltransport.dto.card_dto.MyOrganizationCardDTO;
import com.vbcodes.schooltransport.dto.card_dto.MyVehicleCardDTO;
import com.vbcodes.schooltransport.dto.card_dto.StudentCardDTO;
import com.vbcodes.schooltransport.dto.card_dto.VehicleCardDTO;
import com.vbcodes.schooltransport.entity.AppUser;
import com.vbcodes.schooltransport.entity.Driver;
import com.vbcodes.schooltransport.entity.Organization;
import com.vbcodes.schooltransport.entity.Student;
import com.vbcodes.schooltransport.projection.DriverCardProjection;
import com.vbcodes.schooltransport.projection.MyDriverProjection;
import com.vbcodes.schooltransport.projection.MyVehicleProjection;
import com.vbcodes.schooltransport.projection.StudentCardProjection;
import com.vbcodes.schooltransport.projection.VehicleCardProjection;
import com.vbcodes.schooltransport.repository.DriverRepository;
import com.vbcodes.schooltransport.repository.DriverVehicleMappingRepository;
import com.vbcodes.schooltransport.repository.StudentRepository;
import com.vbcodes.schooltransport.repository.StudentVehicleMappingRepository;
import com.vbcodes.schooltransport.repository.VehicleRepository;
import com.vbcodes.schooltransport.utils.CurrentUserUtil;

@Service
public class SummaryService {
    
    private DriverRepository driverRepository;
    private VehicleRepository vehicleRepository;
    private StudentRepository studentRepository;
    private DriverVehicleMappingRepository dvmRepository;
    private StudentVehicleMappingRepository svmRepository;
    private OrgService orgService;

    @Autowired
    public SummaryService(DriverRepository driverRepository, VehicleRepository vehicleRepository, StudentRepository studentRepository, DriverVehicleMappingRepository dvmRepository, StudentVehicleMappingRepository svmRepository, OrgService orgService){
        this.orgService=orgService;
        this.driverRepository=driverRepository;
        this.vehicleRepository=vehicleRepository;
        this.studentRepository=studentRepository;
        this.dvmRepository=dvmRepository;
        this.svmRepository=svmRepository;
    }

    public List<BaseCardDTO> getSummaryCards(){
        AppUser currentAppUser = CurrentUserUtil.getCurrentUser();
        String currentUserRole = currentAppUser.getRoles();

        List<BaseCardDTO> summaryCardsList = new ArrayList<>();
        switch (currentUserRole) {
            case "ROLE_ORGANIZATION":
                Organization currentOrg = orgService.getOrganizationByAppUser(currentAppUser);

                DriverCardProjection driverCardProjection = driverRepository.getDriverCardDetailsForOrganization(currentOrg.getOrgId());
                DriverCardDTO driverCardDTO = new DriverCardDTO(driverCardProjection.getTotalDrivers(), driverCardProjection.getUnmappedDrivers(), driverCardProjection.getDriversInCommute());
                summaryCardsList.add(driverCardDTO);

                VehicleCardProjection vehicleCardProjection = vehicleRepository.getVehicleCardDetailsForOrganization(currentOrg.getOrgId());
                VehicleCardDTO vehicleCardDTO = new VehicleCardDTO(vehicleCardProjection.getTotalVehicles(), vehicleCardProjection.getUnmappedVehicles(), vehicleCardProjection.getVehiclesInCommute());
                summaryCardsList.add(vehicleCardDTO);

                StudentCardProjection studentCardProjection = studentRepository.getStudentCardDetailsForOrganization(currentOrg.getOrgId());
                StudentCardDTO studentCardDTO = new StudentCardDTO(studentCardProjection.getTotalStudents(), studentCardProjection.getUnmappedStudents());
                summaryCardsList.add(studentCardDTO);

                MappingCardDTO mappingCardDTO = new MappingCardDTO(
                    String.format("%d out of %d drivers mapped", driverCardDTO.getTotalDrivers()-driverCardDTO.getUnmappedDrivers(), driverCardDTO.getTotalDrivers()),
                    String.format("%d out of %d students mapped", studentCardDTO.getTotalStudents()-studentCardDTO.getUnmappedStudents(), studentCardDTO.getTotalStudents()),
                    String.format("%d out of %d vehicles mapped", vehicleCardDTO.getTotalVehicles()-vehicleCardDTO.getUnmappedVehicles(), vehicleCardDTO.getTotalVehicles())
                );
                summaryCardsList.add(mappingCardDTO);
            break;
            case "ROLE_DRIVER":
                Driver currentDriver = driverRepository.findDriverByAppUser(currentAppUser);

                MyVehicleProjection myVehicleProjection = dvmRepository.getVehicleCardDetailsForDriver(currentDriver.getDriverId());
                MyVehicleCardDTO mvCardDTO = new MyVehicleCardDTO(myVehicleProjection.getVehicleRegistrationNumber(), myVehicleProjection.getVehicleNumber(), myVehicleProjection.getVehicleType());
                summaryCardsList.add(mvCardDTO);

                MyOrganizationCardDTO moCardDTO = new MyOrganizationCardDTO(currentDriver.getOrganization().getOrgName(), currentDriver.getOrganization().getOrgId(), currentDriver.getOrganization().getContactNumber());
                summaryCardsList.add(moCardDTO);
                
            break;
            case "ROLE_PARENT":
                Student currentStudent = studentRepository.findByAppUser(currentAppUser);

                MyVehicleProjection myVehicleProjection2 = svmRepository.getVehicleCardDetailsForStudent(currentStudent.getStudentId());
                MyVehicleCardDTO mvCardDTO2 = new MyVehicleCardDTO(myVehicleProjection2.getVehicleRegistrationNumber(), myVehicleProjection2.getVehicleNumber(), myVehicleProjection2.getVehicleType());
                summaryCardsList.add(mvCardDTO2);

                MyOrganizationCardDTO moCardDTO2 = new MyOrganizationCardDTO(currentStudent.getOrganization().getOrgName(), currentStudent.getOrganization().getOrgId(), currentStudent.getOrganization().getContactNumber());
                summaryCardsList.add(moCardDTO2);

                MyDriverProjection myDriverCardProjection = svmRepository.findDriverCardDetailsForStudent(currentStudent.getStudentId());
                MyDriverCardDTO myDriverCardDTO = new MyDriverCardDTO(myDriverCardProjection.getDriverName(),myDriverCardProjection.getDriverContactNumber());
                summaryCardsList.add(myDriverCardDTO);
            break;
            default:
            break;
        }

        return summaryCardsList;
    }
}
