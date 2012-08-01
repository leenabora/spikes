package com.tesco.spike.service;

import com.tesco.spike.dao.ClockResultDao;
import com.tesco.spike.vo.ClockResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clockService")
public class ClockService {

    @Autowired
    private ClockResultParser clockResultParser;

    @Autowired
    private ClockResultDao clockResultDao;

    @Autowired
    private JsonConverter jsonConverter;

    public ClockService() {

    }

    public ClockService(ClockResultParser parser, ClockResultDao dao,JsonConverter jsonConverter) {
        this.clockResultParser = parser;
        this.clockResultDao = dao;
        this.jsonConverter = jsonConverter;
    }

    public void ingestClockResult(String clockResultXmlString) throws Exception {
        ClockResult clockResult = clockResultParser.parse(clockResultXmlString);

        clockResult.setClockResultString(jsonConverter.fromXml(clockResultXmlString));

        clockResultDao.ingestClockResult(clockResult);
    }
}
