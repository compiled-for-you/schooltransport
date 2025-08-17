package com.vbcodes.schooltransport.dto;

import lombok.Data;

@Data
public class ParentDTO extends AppUserDTO{
    
    private int parentId;
    private String parentName;
    private String email;
    private String contact;

}
