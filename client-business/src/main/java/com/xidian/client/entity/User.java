package com.xidian.client.entity;

/**
 * Created by LvLiuWei on 2017/8/18.
 */
public class User {

    private String userId;

    private String userName;

    private String email;

    private String password;

    private String company;

    private String userAcount;

    private String pk;

    public User() {}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getUserAcount() {
        return userAcount;
    }

    public void setUserAcount(String userAcount) {
        this.userAcount = userAcount;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String toString() {
        return "User:[userName=" + userName +
                ", userId=" + userId
                + ", email=" + email
                + ", password=" + password
                + ", company=" + company
                + ", userAccount=" + userAcount
                + ", pk=" + pk + "]";
    }

}
