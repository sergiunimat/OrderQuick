package com.example.orderquick;

public class EmployeeViewModel {

    private int imgSrc;
    private int empId;
    private String empName;
    private String empTel;
    private String emWage;

    public EmployeeViewModel(int imgSrc, int empId, String empName, String empTel, String emWage) {
        this.imgSrc = imgSrc;
        this.empId = empId;
        this.empName = empName;
        this.empTel = empTel;
        this.emWage = emWage;
    }

    public int getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(int imgSrc) {
        this.imgSrc = imgSrc;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpTel() {
        return empTel;
    }

    public void setEmpTel(String empTel) {
        this.empTel = empTel;
    }

    public String getEmWage() {
        return emWage;
    }

    public void setEmWage(String emWage) {
        this.emWage = emWage;
    }
}
