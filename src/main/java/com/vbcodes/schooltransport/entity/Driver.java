package com.vbcodes.schooltransport.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "drivers")
public class Driver {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_id")
    private int driverId;

    @Column(name = "full_name", nullable = false, length = 100)
    private String driverName;

    @Column(name = "license_number", nullable = false, length = 50)
    private String licenseNumber;

    @Column(name = "contact_number", nullable = false, length = 20)
    private String contactNumber; 

    @Column(nullable = false, length = 200)
    private String address;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="vehicle_id", referencedColumnName = "vehicle_id")
    private Vehicle vehicle;

    @OneToOne
    // @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", updatable = false)
    private AppUser appUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", referencedColumnName = "org_id") 
    @JsonIgnore
    private Organization organization;

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setOrganization(Organization organization){
        this.organization=organization;
    }

    public Organization geOrganization(){
        return organization;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser){
        this.appUser = appUser;
    }
    
    @Override
    public String toString() {
        return "Driver [driverId=" + driverId + ", driverName=" + driverName + ", licenseNumber=" + licenseNumber
                + ", contactNumber=" + contactNumber + ", address=" + address + ", vehicle=" + vehicle + "]";
    }

}
