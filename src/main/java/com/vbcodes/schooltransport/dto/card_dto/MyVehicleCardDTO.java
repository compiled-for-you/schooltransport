package com.vbcodes.schooltransport.dto.card_dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MyVehicleCardDTO extends BaseCardDTO{
    private String vehicleRegistrationNumber;
    private String vehicleNumber;
    private String vehicleType;

    public MyVehicleCardDTO(String vehicleRegistrationNumber, String vehicleNumber, String vehicleType){
        super("My Vehicle", "MyVehicleCard", "/vehicles");
        this.vehicleRegistrationNumber=vehicleRegistrationNumber;
        this.vehicleNumber=vehicleNumber;
        this.vehicleType=vehicleType;
    }
}
