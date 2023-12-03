package com.vbcodes.schooltransport.dto;

public class StudentDTO {
    
    private String studentName;
    private String address;
    private int grade;
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
    @Override
    public String toString() {
        return "StudentDTO [studentName=" + studentName + ", address=" + address + ", grade=" + grade + "]";
    }

    
}
