package com.example.demo.entity;
//管理员信息表
public class Admins {
    private int adminId;
    private String adminPhone;
    private String adminPassword;
    private String adminName;
    private String adminAccount;

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }


    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminAccount() {
        return adminAccount;
    }

    public void setAdminAccount(String adminAccount) {
        this.adminAccount = adminAccount;
    }

    @Override
    public String toString() {
        return "Admins{" +
                "adminId=" + adminId +
                ", adminPhone='" + adminPhone + '\'' +
                ", adminPassword='" + adminPassword + '\'' +
                ", adminName='" + adminName + '\'' +
                ", adminAccount='" + adminAccount + '\'' +
                '}';
    }
}
