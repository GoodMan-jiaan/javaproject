package com.example.demo.entity;
//用户信息表
public class User {
    private int userId;
    private String userPhone;
    private String userPassword;
    private String userName;
    private int userAge;
    private String userSex;
    private String userIdcard;
    private String userKinds;
    private String userImageUrl;
    private String userPracticalName;
    private int userBeFollowTotal;
    private int userFollowTotal;
    private int userUpdateTotal;


    public int getUserUpdateTotal() {
        return userUpdateTotal;
    }

    public void setUserUpdateTotal(int userUpdateTotal) {
        this.userUpdateTotal = userUpdateTotal;
    }

    public int getUserBeFollowTotal() {
        return userBeFollowTotal;
    }

    public void setUserBeFollowTotal(int userBeFollowTotal) {
        this.userBeFollowTotal = userBeFollowTotal;
    }

    public int getUserFollowTotal() {
        return userFollowTotal;
    }

    public void setUserFollowTotal(int userFollowTotal) {
        this.userFollowTotal = userFollowTotal;
    }

    public String getUserPracticalName() {
        return userPracticalName;
    }

    public void setUserPracticalName(String userPracticalName) {
        this.userPracticalName = userPracticalName;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserIdcard() {
        return userIdcard;
    }

    public void setUserIdcard(String userIdcard) {
        this.userIdcard = userIdcard;
    }

    public String getUserKinds() {
        return userKinds;
    }

    public void setUserKinds(String userKinds) {
        this.userKinds = userKinds;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userPhone='" + userPhone + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userName='" + userName + '\'' +
                ", userAge=" + userAge +
                ", userSex='" + userSex + '\'' +
                ", userIdcard='" + userIdcard + '\'' +
                ", userKinds='" + userKinds + '\'' +
                ", userImageUrl='" + userImageUrl + '\'' +
                ", userPracticalName='" + userPracticalName + '\'' +
                ", userBeFollowTotal=" + userBeFollowTotal +
                ", userFollowTotal=" + userFollowTotal +
                ", userUpdateTotal=" + userUpdateTotal +
                '}';
    }
}
