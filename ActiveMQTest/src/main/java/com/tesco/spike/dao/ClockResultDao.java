package com.tesco.spike.dao;

import com.tesco.spike.vo.ClockResult;

import java.util.List;


public interface ClockResultDao {
    void ingestClockResult(ClockResult clockResult);

    String getClockResultByTransactioNo(String transactionNo);

    List<String> getClockResultByLoyaltyCardNo(String loyaltyCardNo);
}