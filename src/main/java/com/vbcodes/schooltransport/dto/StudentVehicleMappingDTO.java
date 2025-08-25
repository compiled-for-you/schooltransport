package com.vbcodes.schooltransport.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentVehicleMappingDTO {
    private Integer studentID;
    private Integer vehicleID;
}
