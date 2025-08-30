package com.vbcodes.schooltransport.dto.card_dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseCardDTO {
    protected String cardTitle;
    protected String cardType;
    protected String linkTo;
}
