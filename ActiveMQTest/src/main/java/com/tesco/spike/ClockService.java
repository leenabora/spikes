package com.tesco.spike;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClockService {

    @Autowired
    ClockResultParser clockResultParser;

    @Autowired
    ClockResultDao clockResultDao;

    public void ingestClockResult(String clockResultXmlString) throws Exception {
        ClockResult clockResult = clockResultParser.parse(clockResultXmlString);

        clockResultDao.ingestClockResult(clockResult);
    }
}
