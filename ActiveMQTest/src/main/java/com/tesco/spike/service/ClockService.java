package com.tesco.spike.service;

import com.tesco.spike.dao.ClockResultDao;
import com.tesco.spike.vo.ClockResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClockService {

    @Autowired
    private ClockResultParser clockResultParser;

    @Autowired
    private ClockResultDao clockResultDao;

    public ClockService() {
    }

    public ClockService(ClockResultParser parser, ClockResultDao dao) {
        this.clockResultParser = parser;
        this.clockResultDao = dao;
    }

    public void ingestClockResult(String clockResultXmlString) throws Exception {
        ClockResult clockResult = clockResultParser.parse(clockResultXmlString);

        clockResultDao.ingestClockResult(clockResult);
    }
}
