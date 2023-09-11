package com.vbcodes.schooltransport.dto;

import com.vbcodes.schooltransport.entity.OrgType;

public class OrganizationDTO extends AppUserDTO{
    private String orgName;
    private String ownerName;
    private String address;    
    private String contactNumber;
    private OrgType orgType;
    
    public String getOrgName() {
        return orgName;
    }
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
    public String getOwnerName() {
        return ownerName;
    }
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getContactNumber() {
        return contactNumber;
    }
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    public OrgType getOrgType() {
        return orgType;
    }
    public void setOrgType(OrgType orgType) {
        this.orgType = orgType;
    }
    @Override
    public String toString() {
        
        return super.toString() + "OrganizationDTO [orgName=" + orgName + ", ownerName=" + ownerName + ", address=" + address
                + ", contactNumber=" + contactNumber + ", orgType=" + orgType + "]";
    }
}