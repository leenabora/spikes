package com.tesco.spike.dao.impl;

import com.tesco.spike.dao.ClockResultDao;
import com.tesco.spike.vo.ClockResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;


@Repository
public class ClockResultJDBCDao implements ClockResultDao {

    private JdbcTemplate jdbcTemplate;

    public String getMessage(String messageKey) {
        return jdbcTemplate.queryForObject(
                "select message from messages where messagekey = ?",
                String.class, messageKey);
    }

    @Autowired
    public void createTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void ingestClockResult(ClockResult clockResult) {
        String sql = "insert into clockresult(transaction_no, loyalty_card_no, clock_result) values " +
                "(?,?,?)";

        Object[] obj = {clockResult.getTransactionNo(), clockResult.getLoyaltyCardNo(), clockResult.getClockResultXmlString()};
        jdbcTemplate.update(sql, obj);
    }
}
