package com.tesco.spike.dao.impl;

import com.tesco.spike.vo.ClockResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/app-context.xml")
public class ClockResultJDBCDaoTest {

    @Autowired
    private DataSource dataSource;
    private ClockResultJDBCDao dao = null;

    @Before
    public void before() {
        dao = new ClockResultJDBCDao();
        dao.createTemplate(dataSource);
    }

    @Test
    public void shouldSaveClockResult() {
        dao.ingestClockResult(new ClockResult("l-1", "t-1", "<xml></xml>"));
    }
}
