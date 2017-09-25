package com.wang.yan.mvc.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by ywang on 19/12/15.
 */
@Entity
@Table(name="FEDEX")
public class Fedex {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer id;

    @Column(name="INVOICE_NUMBER")
    private String invoice_number;

    @Column(name="INVOICE_TYPE")
    private String invoice_type;

    @Column(name="INVOICE_DATE")
    private String invoice_date;

    @Column(name="DUE_TYPE")
    private String due_date;

    @Column(name="ACCOUNT_NUMBER")
    private String account_number;

    @Column(name="INVOICE_STATUS")
    private String invoice_status;

    @Column(name="PAID")
    private Double paid;

    @Column(name="CURRENCY")
    private String currency;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInvoice_number() {
        return invoice_number;
    }

    public void setInvoice_number(String invoice_number) {
        this.invoice_number = invoice_number;
    }

    public String getInvoice_type() {
        return invoice_type;
    }

    public void setInvoice_type(String invoice_type) {
        this.invoice_type = invoice_type;
    }

    public String getInvoice_date() {
        return invoice_date;
    }

    public void setInvoice_date(String invoice_date) {
        this.invoice_date = invoice_date;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getInvoice_status() {
        return invoice_status;
    }

    public void setInvoice_status(String invoice_status) {
        this.invoice_status = invoice_status;
    }

    public Double getPaid() {
        return paid;
    }

    public void setPaid(Double paid) {
        this.paid = paid;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
