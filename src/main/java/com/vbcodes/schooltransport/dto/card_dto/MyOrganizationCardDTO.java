package com.vbcodes.schooltransport.dto.card_dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MyOrganizationCardDTO extends BaseCardDTO{
    private String orgName;
    private Integer orgId;
    private String orgContactNumber;

    public MyOrganizationCardDTO(String orgName, Integer orgId, String orgContactNumber) {
        super("My Organization", "MyOrganizationCard", "/organization");
        this.orgName = orgName;
        this.orgId = orgId;
        this.orgContactNumber = orgContactNumber;
    }

    
}
