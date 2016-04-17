package com.wang.yan.mvc.model.finance;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by ywang on 17.04.16.
 */
public class StockData implements Serializable {
    private String stockName;
    private String stockCurrency;
    private BigDecimal stockDayHigh;
    private BigDecimal stockDayLow;

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockCurrency() {
        return stockCurrency;
    }

    public void setStockCurrency(String stockCurrency) {
        this.stockCurrency = stockCurrency;
    }

    public BigDecimal getStockDayHigh() {
        return stockDayHigh;
    }

    public void setStockDayHigh(BigDecimal stockDayHigh) {
        this.stockDayHigh = stockDayHigh;
    }

    public BigDecimal getStockDayLow() {
        return stockDayLow;
    }

    public void setStockDayLow(BigDecimal stockDayLow) {
        this.stockDayLow = stockDayLow;
    }
}
