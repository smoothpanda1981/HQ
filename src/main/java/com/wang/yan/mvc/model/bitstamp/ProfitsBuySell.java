package com.wang.yan.mvc.model.bitstamp;

import java.math.BigDecimal;

/**
 * Created by ywang on 09.07.17.
 */
public class ProfitsBuySell {

    private String usd_buy;
    private String usd_sell;
    private String btc_usd_buy;
    private String btc_usd_sell;
    private Integer order_id_buy;
    private Integer order_id_sell;
    private String datetime_buy;
    private String datetime_sell;
    private String fee_buy;
    private String fee_sell;
    private Double btc_buy;
    private Double btc_sell;
    private String type_buy;
    private String type_sell;
    private Integer id_buy;
    private Integer id_sell;
    private Double eur_buy;
    private Double eur_sell;
    private BigDecimal profitAndLose;

    public String getUsd_buy() {
        return usd_buy;
    }

    public void setUsd_buy(String usd_buy) {
        this.usd_buy = usd_buy;
    }

    public String getUsd_sell() {
        return usd_sell;
    }

    public void setUsd_sell(String usd_sell) {
        this.usd_sell = usd_sell;
    }

    public String getBtc_usd_buy() {
        return btc_usd_buy;
    }

    public void setBtc_usd_buy(String btc_usd_buy) {
        this.btc_usd_buy = btc_usd_buy;
    }

    public String getBtc_usd_sell() {
        return btc_usd_sell;
    }

    public void setBtc_usd_sell(String btc_usd_sell) {
        this.btc_usd_sell = btc_usd_sell;
    }

    public Integer getOrder_id_buy() {
        return order_id_buy;
    }

    public void setOrder_id_buy(Integer order_id_buy) {
        this.order_id_buy = order_id_buy;
    }

    public Integer getOrder_id_sell() {
        return order_id_sell;
    }

    public void setOrder_id_sell(Integer order_id_sell) {
        this.order_id_sell = order_id_sell;
    }

    public String getDatetime_buy() {
        return datetime_buy;
    }

    public void setDatetime_buy(String datetime_buy) {
        this.datetime_buy = datetime_buy;
    }

    public String getDatetime_sell() {
        return datetime_sell;
    }

    public void setDatetime_sell(String datetime_sell) {
        this.datetime_sell = datetime_sell;
    }

    public String getFee_buy() {
        return fee_buy;
    }

    public void setFee_buy(String fee_buy) {
        this.fee_buy = fee_buy;
    }

    public String getFee_sell() {
        return fee_sell;
    }

    public void setFee_sell(String fee_sell) {
        this.fee_sell = fee_sell;
    }

    public Double getBtc_buy() {
        return btc_buy;
    }

    public void setBtc_buy(Double btc_buy) {
        this.btc_buy = btc_buy;
    }

    public Double getBtc_sell() {
        return btc_sell;
    }

    public void setBtc_sell(Double btc_sell) {
        this.btc_sell = btc_sell;
    }

    public String getType_buy() {
        return type_buy;
    }

    public void setType_buy(String type_buy) {
        this.type_buy = type_buy;
    }

    public String getType_sell() {
        return type_sell;
    }

    public void setType_sell(String type_sell) {
        this.type_sell = type_sell;
    }

    public Integer getId_buy() {
        return id_buy;
    }

    public void setId_buy(Integer id_buy) {
        this.id_buy = id_buy;
    }

    public Integer getId_sell() {
        return id_sell;
    }

    public void setId_sell(Integer id_sell) {
        this.id_sell = id_sell;
    }

    public Double getEur_buy() {
        return eur_buy;
    }

    public void setEur_buy(Double eur_buy) {
        this.eur_buy = eur_buy;
    }

    public Double getEur_sell() {
        return eur_sell;
    }

    public void setEur_sell(Double eur_sell) {
        this.eur_sell = eur_sell;
    }

    public BigDecimal getProfitAndLose() {
        return profitAndLose;
    }

    public void setProfitAndLose(BigDecimal profitAndLose) {
        this.profitAndLose = profitAndLose;
    }
}
