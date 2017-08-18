package com.wang.yan.mvc.model.bitstamp;

/**
 * Created by ywang on 09.07.17.
 */
public class UserTransaction {
    private String usd;
    private String btc_usd;
    private Integer order_id;
    private String datetime;
    private String fee;
    private Double btc;
    private String type;
    private Integer id;
    private Double eur;
    private Double eth;
    private Double eth_usd;


    public String getUsd() {
        return usd;
    }

    public void setUsd(String usd) {
        this.usd = usd;
    }

    public String getBtc_usd() {
        return btc_usd;
    }

    public void setBtc_usd(String btc_usd) {
        this.btc_usd = btc_usd;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public Double getBtc() {
        return btc;
    }

    public void setBtc(Double btc) {
        this.btc = btc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getEur() {
        return eur;
    }

    public void setEur(Double eur) {
        this.eur = eur;
    }

    public Double getEth() {
        return eth;
    }

    public void setEth(Double eth) {
        this.eth = eth;
    }

    public Double getEth_usd() {
        return eth_usd;
    }

    public void setEth_usd(Double eth_usd) {
        this.eth_usd = eth_usd;
    }
}
