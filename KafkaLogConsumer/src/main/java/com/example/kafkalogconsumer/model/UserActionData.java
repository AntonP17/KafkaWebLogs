package com.example.kafkalogconsumer.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_action_data")
public class UserActionData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String action;

    public UserActionData() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return "UserActionData{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", action='" + action + '\'' +
                '}';
    }
}
