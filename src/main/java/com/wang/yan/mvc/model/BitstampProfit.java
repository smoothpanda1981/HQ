package com.wang.yan.mvc.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ywang on 23/09/17.
 */
@Entity
@Table(name="BITSTAMP_PROFIT")
public class BitstampProfit {
    private Long id;
    private Double profit;
    private Date creationDate;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    @Column(name = "profit", nullable = false)
    public Double getProfit() {
        return profit;
    }

    @Column(name = "creation_date")
    public Date getCreationDate() {
        return creationDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
