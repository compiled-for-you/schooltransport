package com.vbcodes.schooltransport.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vbcodes.schooltransport.dto.DriverDTO;
import com.vbcodes.schooltransport.dto.StudentParentFormDTO;
import com.vbcodes.schooltransport.entity.Driver;
import com.vbcodes.schooltransport.entity.Parent;
import com.vbcodes.schooltransport.entity.Student;

@Configuration
public class ProjectModelMapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Skip IDs for Parent
        modelMapper.typeMap(StudentParentFormDTO.class, Parent.class)
                   .addMappings(m -> m.skip(Parent::setParentId));

        // Skip IDs for Student
        modelMapper.typeMap(StudentParentFormDTO.class, Student.class)
                   .addMappings(m -> m.skip(Student::setStudentId));

        // Skip IDs for Driver
        modelMapper.typeMap(DriverDTO.class, Driver.class)
                   .addMappings(m -> m.skip(Driver::setDriverId));

        return modelMapper;
    }
}
