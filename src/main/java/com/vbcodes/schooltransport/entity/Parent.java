package com.vbcodes.schooltransport.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "parents")
public class Parent {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parent_id")
    private int parentId;

    @Column(name = "full_name", nullable = false, length = 100)
    private String parentName;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 20)
    private String contact;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", updatable = false)
    private AppUser appUser;

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setAppUser(AppUser appUser){
        this.appUser=appUser;
    }
    public AppUser getAppUser() {
        return appUser;
    }

    @Override
    public String toString() {
        return "Parent [parentId=" + parentId + ", parentName=" + parentName + ", email=" + email + ", contact="
                + contact + "]";
    }

    
}