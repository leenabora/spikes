package com.tesco.spike.service;


import com.tesco.spike.dao.ClockResultDao;
import com.tesco.spike.vo.ClockResult;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class ClockServiceTest {

    private ClockService clockService;

    @Mock
    private ClockResultParser parser;
    private String clockResultXmlString;
    @Mock
    private ClockResultDao dao;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);

        clockService = new ClockService(parser, dao);
        clockResultXmlString = "clock result xml";
    }


    @Test
    public void shouldParseClockResultXml() throws Exception {
        clockService.ingestClockResult(clockResultXmlString);

        verify(parser).parse(clockResultXmlString);
    }

    @Test
    public void shouldInvokeDao() throws Exception {
        ClockResult clockResult = mock(ClockResult.class);
        when(parser.parse(clockResultXmlString)).thenReturn(clockResult);

        clockService.ingestClockResult(clockResultXmlString);

        verify(dao).ingestClockResult(clockResult);
    }
}
