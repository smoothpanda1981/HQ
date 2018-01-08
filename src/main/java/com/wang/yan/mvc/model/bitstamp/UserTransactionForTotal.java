package com.wang.yan.mvc.model.bitstamp;

/**
 * Created by ywang on 09.07.17.
 */
public class UserTransactionForTotal {
    private int totalBuy;
    private int totalSell;
    private int totalDeposit;
    private int totalWithdraw;

    public int getTotalBuy() {
        return totalBuy;
    }

    public void setTotalBuy(int totalBuy) {
        this.totalBuy = totalBuy;
    }

    public int getTotalSell() {
        return totalSell;
    }

    public void setTotalSell(int totalSell) {
        this.totalSell = totalSell;
    }

    public int getTotalDeposit() {
        return totalDeposit;
    }

    public void setTotalDeposit(int totalDeposit) {
        this.totalDeposit = totalDeposit;
    }

    public int getTotalWithdraw() {
        return totalWithdraw;
    }

    public void setTotalWithdraw(int totalWithdraw) {
        this.totalWithdraw = totalWithdraw;
    }
}
