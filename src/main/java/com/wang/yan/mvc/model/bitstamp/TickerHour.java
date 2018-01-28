package com.wang.yan.mvc.model.bitstamp;

import javax.persistence.*;

@Entity
@Table(name = "TICKER_HOUR")
public class TickerHour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "HIGH")
    private Double high;

    @Column(name = "LAST")
    private Double last;

    @Column(name = "TIMESTAMP")
    private String timestamp;

    @Column(name = "BID")
    private Double bid;

    @Column(name = "VWAP")
    private Double vwap;

    @Column(name = "VOLUME")
    private Double volume;

    @Column(name = "LOW")
    private Double low;

    @Column(name = "ASK")
    private Double ask;

    @Column(name = "OPEN")
    private Double open;

    @Column(name = "CRYPTO_CURRENCY")
    private String cryptoCurrency;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLast() {
        return last;
    }

    public void setLast(Double last) {
        this.last = last;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public Double getVwap() {
        return vwap;
    }

    public void setVwap(Double vwap) {
        this.vwap = vwap;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public String getCryptoCurrency() {
        return cryptoCurrency;
    }

    public void setCryptoCurrency(String cryptoCurrency) {
        this.cryptoCurrency = cryptoCurrency;
    }
}
