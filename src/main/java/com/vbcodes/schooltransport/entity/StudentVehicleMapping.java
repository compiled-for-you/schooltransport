package com.vbcodes.schooltransport.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table(name = "student_vehicle_mappings",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"organization_id", "student_id", "vehicle_id"})
        })
public class StudentVehicleMapping {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mappingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", referencedColumnName = "org_id", nullable = false)
    private Organization organization;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", referencedColumnName = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", referencedColumnName = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @Column(nullable = false, updatable = false)
    private LocalDateTime firstLoggedDate;

    @Column(nullable = false)
    private LocalDateTime updatedDate;

    private boolean isActive;

    @PrePersist
    public void onCreate(){
        this.firstLoggedDate=LocalDateTime.now();
        this.updatedDate=LocalDateTime.now();
        this.isActive=true;
    }

    @PreUpdate
    public void onUpdate(){
        this.updatedDate=LocalDateTime.now();
    }
}
