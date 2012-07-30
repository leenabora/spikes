package com.tesco.spike.vo;

public class ClockResult {

    private final String loyaltyCardNo;
    private final String transactionNo;
    private final String clockResultXmlString;

    public ClockResult(String loyaltyCardNo, String transactionNo, String clockResultXmlString) {
        this.loyaltyCardNo = loyaltyCardNo;
        this.transactionNo = transactionNo;
        this.clockResultXmlString = clockResultXmlString;
    }

    public String getLoyaltyCardNo() {
        return loyaltyCardNo;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public String getClockResultXmlString() {
        return clockResultXmlString;
    }
}
