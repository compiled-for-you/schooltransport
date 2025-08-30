package com.vbcodes.schooltransport.dto.card_dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MyDriverCardDTO extends BaseCardDTO{
    private String driverName;
    private String driverContactNumber;

    public MyDriverCardDTO(String driverName, String driverContactNumber){
        super("My Driver", "MyDriverCard", "/driver");
        this.driverName=driverName;
        this.driverContactNumber=driverContactNumber;
    }
}
