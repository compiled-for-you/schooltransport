package com.vbcodes.schooltransport.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "organizations")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "org_id")
    private Integer orgId;

    @Column(name = "org_name", nullable = false, length = 100)
    private String orgName;

    @Column(name = "owner_name", nullable = false, length = 50)
    private String ownerName;

    @Column(length = 200)
    private String address;

    @Column(name = "contact_number", nullable = false, length = 20)
    private String contactNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "org_type", nullable = false)
    private OrgType orgType;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", updatable = false)
    private AppUser appUser;

    public Organization(){}
    public Organization(String orgName, String ownerName, String address, String contactNumber, OrgType orgType) {
        this.orgName = orgName;
        this.ownerName = ownerName;
        this.address = address;
        this.contactNumber = contactNumber;
        this.orgType = orgType;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

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

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser){
        this.appUser = appUser;
    }
    
    @Override
    public String toString() {
        return "Organization [orgId=" + orgId + ", orgName=" + orgName + ", ownerName=" + ownerName + ", address="
                + address + ", contactNumber=" + contactNumber + ", orgType=" + orgType + "] "+ appUser.toString();
    }
}

