package com.wang.yan.mvc.model.bitstamp;

import org.apache.commons.fileupload.util.LimitedInputStream;

import java.util.List;
import java.util.Map;

/**
 * Created by ywang on 09.07.17.
 */
public class OrderBook {
    private String timestamp;
    private List bids;
    private List asks;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public List getBids() {
        return bids;
    }

    public void setBids(List bids) {
        this.bids = bids;
    }

    public List getAsks() {
        return asks;
    }

    public void setAsks(List asks) {
        this.asks = asks;
    }
}
