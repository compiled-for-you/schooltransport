package com.vbcodes.schooltransport.dto;

public class ParentDTO extends AppUserDTO{
    
    private String parentName;
    private String email;
    private String contact;
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
    @Override
    public String toString() {
        return "ParentDTO [parentName=" + parentName + ", email=" + email + ", contact=" + contact + "]";
    }

}
