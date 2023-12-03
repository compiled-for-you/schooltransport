package com.vbcodes.schooltransport.dto;

public class StudentParentFormDTO extends AppUserDTO{
    
    private String studentName;
    private String address;
    private int grade;
    private int orgId;
    private String parentName;
    private String email;
    private String contact;

    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getGrade() {
        return grade;
    }
    public void setGrade(int grade) {
        this.grade = grade;
    }
    public int getOrgId() {
        return orgId;
    }
    public void setOrgId(int orgId) {
        this.orgId = orgId;
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
}
