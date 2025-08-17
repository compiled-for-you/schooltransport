package com.vbcodes.schooltransport.dto;

import lombok.Data;

@Data
public class DriverDTO extends AppUserDTO{
        
    private int driverId;
    private String driverName;
    private String licenseNumber;
    private String contactNumber;
    private String address;
    private int orgId;
   
}