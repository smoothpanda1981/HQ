package com.wang.yan.mvc.model.finance;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by ywang on 17.04.16.
 */
public class FxData implements Serializable {
    private String fxSymbol;
    private BigDecimal fxPrice;

    public String getFxSymbol() {
        return fxSymbol;
    }

    public void setFxSymbol(String fxSymbol) {
        this.fxSymbol = fxSymbol;
    }

    public BigDecimal getFxPrice() {
        return fxPrice;
    }

    public void setFxPrice(BigDecimal fxPrice) {
        this.fxPrice = fxPrice;
    }
}
