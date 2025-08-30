package com.vbcodes.schooltransport.dto.card_dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class DriverCardDTO extends BaseCardDTO{
    private Long totalDrivers;
    private Long unmappedDrivers;
    private Long driversInCommute;

    public DriverCardDTO(Long totalDrivers, Long unmappedDrivers, Long driversInCommute){
        super("Drivers", "DriverCard", "/drivers");
        this.totalDrivers=totalDrivers;
        this.unmappedDrivers=unmappedDrivers;
        this.driversInCommute=driversInCommute;
    }
}
