package com.vbcodes.schooltransport.dto.card_dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VehicleCardDTO extends BaseCardDTO{
    private Long totalVehicles;
    private Long unmappedVehicles;
    private Long vehiclesInCommute;

    public VehicleCardDTO(Long totalVehicles, Long unmappedVehicles, Long vehiclesInCommute){
        super("Vehicles", "VehicleCard", "/vehicles");
        this.totalVehicles=totalVehicles;
        this.unmappedVehicles=unmappedVehicles;
        this.vehiclesInCommute=vehiclesInCommute;
    }
}
