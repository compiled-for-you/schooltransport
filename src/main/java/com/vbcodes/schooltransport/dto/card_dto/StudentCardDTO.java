package com.vbcodes.schooltransport.dto.card_dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentCardDTO extends BaseCardDTO{
    private Long totalStudents;
    private Long unmappedStudents;

    public StudentCardDTO(Long totalStudents, Long unmappedStudents){
        super("Students", "StudentCard", "/students");
        this.totalStudents=totalStudents;
        this.unmappedStudents=unmappedStudents;
    }

}
