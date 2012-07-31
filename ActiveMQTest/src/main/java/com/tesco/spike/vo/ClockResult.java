package com.tesco.spike.vo;

public class ClockResult {

    private String loyaltyCardNo;
    private String transactionNo;
    private String clockResultString;

    public ClockResult(String loyaltyCardNo, String transactionNo) {
        this.loyaltyCardNo = loyaltyCardNo;
        this.transactionNo = transactionNo;
    }

    public void setClockResultString(String clockResultString) {
        this.clockResultString = clockResultString;
    }

    public String getLoyaltyCardNo() {
        return loyaltyCardNo;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public String getClockResultString() {
        return clockResultString;
    }
}
