package com.vbcodes.schooltransport.dto.card_dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MappingCardDTO extends BaseCardDTO{
    private String driversMapping;
    private String studentsMapping;
    private String vehiclesMapping;

    public MappingCardDTO(String driversMapping, String studentsMapping, String vehiclesMapping) {
        super("Mappings", "MappingCard", "/mappings");
        this.driversMapping = driversMapping;
        this.studentsMapping = studentsMapping;
        this.vehiclesMapping = vehiclesMapping;
    }
}
