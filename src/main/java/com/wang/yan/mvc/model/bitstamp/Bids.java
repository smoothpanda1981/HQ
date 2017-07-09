package com.wang.yan.mvc.model.bitstamp;

/**
 * Created by ywang on 09.07.17.
 */
public class Bids {
    private double price;
    private double btcQuantity;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getBtcQuantity() {
        return btcQuantity;
    }

    public void setBtcQuantity(double btcQuantity) {
        this.btcQuantity = btcQuantity;
    }
}
