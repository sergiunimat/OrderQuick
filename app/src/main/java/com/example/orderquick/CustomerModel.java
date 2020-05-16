package com.example.orderquick;

public class CustomerModel {
    private int CustomerId;
    private String CustomerName;
    private String TelephoneNumber;
    private String Password;
    private int Role;

    public CustomerModel(int customerId, String customerName, String telephoneNumber, String password, int role) {
        CustomerId = customerId;
        CustomerName = customerName;
        TelephoneNumber = telephoneNumber;
        Password = password;
        Role = role;
    }

    public CustomerModel() {
    }

    @Override
    public String toString() {
        return "CustomerModel{" +
                "CustomerId=" + CustomerId +
                ", CustomerName='" + CustomerName + '\'' +
                ", TelephoneNumber='" + TelephoneNumber + '\'' +
                ", Password='" + Password + '\'' +
                ", Role=" + Role +
                '}';
    }

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        CustomerId = customerId;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getTelephoneNumber() {
        return TelephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        TelephoneNumber = telephoneNumber;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getRole() {
        return Role;
    }

    public void setRole(int role) {
        Role = role;
    }
}
