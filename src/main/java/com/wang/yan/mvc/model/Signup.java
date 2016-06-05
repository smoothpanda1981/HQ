package com.wang.yan.mvc.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by ywang on 19/12/15.
 */
@Entity
@Table(name="USERS")
public class Signup {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(name="company", nullable=false)
    private String company;

    @NotEmpty
    @Column(name="first_name", nullable=false)
    private String first_name;


    @NotEmpty
    @Column(name="last_name", nullable=false)
    private String last_name;

    @NotEmpty
    @Column(name="email", nullable=false)
    private String email;

    @NotEmpty
    @Column(name="street", nullable=false)
    private String street;

    @NotEmpty
    @Column(name="city", nullable=false)
    private String city;

    @NotEmpty
    @Column(name="state", nullable=false)
    private String state;

    @NotEmpty
    @Column(name="zip", nullable=false)
    private String zip;

    @NotEmpty
    @Column(name="phone", nullable=false)
    private String phone;

    @OneToOne
    @JoinColumn(name = "ID")
    private Login login;

    private String password;

    private String password_confirmation;

    private String username;

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_confirmation() {
        return password_confirmation;
    }

    public void setPassword_confirmation(String password_confirmation) {
        this.password_confirmation = password_confirmation;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
