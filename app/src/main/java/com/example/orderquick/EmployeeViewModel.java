package com.example.orderquick;

import android.os.Parcel;
import android.os.Parcelable;

public class EmployeeViewModel implements Parcelable {

    private int imgSrc;
    private int empId;
    private String empName;
    private String empTel;
    private String emWage;
    private int deleteImgSrc;

    public EmployeeViewModel(int imgSrc, int empId, String empName, String empTel, String emWage,int deleteImgSrc) {
        this.imgSrc = imgSrc;
        this.empId = empId;
        this.empName = empName;
        this.empTel = empTel;
        this.emWage = emWage;
        this.deleteImgSrc = deleteImgSrc;
    }

    protected EmployeeViewModel(Parcel in) {
        imgSrc = in.readInt();
        empId = in.readInt();
        empName = in.readString();
        empTel = in.readString();
        emWage = in.readString();
        deleteImgSrc = in.readInt();
    }

    public static final Creator<EmployeeViewModel> CREATOR = new Creator<EmployeeViewModel>() {
        @Override
        public EmployeeViewModel createFromParcel(Parcel in) {
            return new EmployeeViewModel(in);
        }

        @Override
        public EmployeeViewModel[] newArray(int size) {
            return new EmployeeViewModel[size];
        }
    };

    public int getDeleteImgSrc() {
        return deleteImgSrc;
    }

    public void setDeleteImgSrc(int deleteImgSrc) {
        this.deleteImgSrc = deleteImgSrc;
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

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imgSrc);
        dest.writeInt(empId);
        dest.writeString(empName);
        dest.writeString(empTel);
        dest.writeString(emWage);
        dest.writeInt(deleteImgSrc);
    }
}
