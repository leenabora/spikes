package com.tesco.spike.dao.impl;

import com.tesco.spike.vo.ClockResult;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import static org.junit.Assert.assertThat;

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

        cleanDatabase();
    }

    private void cleanDatabase() {
        String query = "DELETE FROM ClockResult";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(query);
    }

    @Test
    public void shouldSaveClockResult() {
        dao.ingestClockResult(new ClockResult("l-1", "t-1", "<xml></xml>"));

        String clockResult = dao.getClockResultByTransactioNo("t-1");
        assertThat(clockResult, Is.is("<xml></xml>"));

        clockResult = dao.getClockResultByLoyaltyCardNo("l-1");
        assertThat(clockResult, Is.is("<xml></xml>"));
    }
}
