package com.wang.yan.mvc.model;

/**
 * Created by ywang on 21/11/15.
 */
public class Student{
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone(String s) {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
