package com.tesco.spike.dao;

import com.tesco.spike.vo.ClockResult;
import org.springframework.stereotype.Repository;


public interface ClockResultDao {
    void ingestClockResult(ClockResult clockResult);
}
