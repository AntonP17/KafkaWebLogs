package com.example.kafkalogproducer.model;

public class UserActionDTO {

    private String userName;
    private String action;

    public UserActionDTO() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "UserActionDTO{" +
                "userName='" + userName + '\'' +
                ", action='" + action + '\'' +
                '}';
    }
}
