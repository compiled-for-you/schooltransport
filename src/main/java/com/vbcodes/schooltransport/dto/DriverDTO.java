package com.vbcodes.schooltransport.dto;

import com.vbcodes.schooltransport.entity.AppUser;
import com.vbcodes.schooltransport.entity.Vehicle;

public class DriverDTO extends AppUserDTO{
        
    private int driverId;
    private String driverName;
    private String licenseNumber;
    private String contactNumber;
    private String address;
    private Vehicle vehicle;
    private int orgId;
    private AppUser appUser;

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

    public int getOrgId() {
        return orgId;
    }
    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public AppUser getAppUser() {
        return appUser;
    }
    
    @Override
    public String toString() {
        return super.toString() + " Driver [driverId=" + driverId + ", driverName=" + driverName + ", licenseNumber=" + licenseNumber
                + ", contactNumber=" + contactNumber + ", address=" + address + ", vehicle=" + vehicle + "]";
    }    
}