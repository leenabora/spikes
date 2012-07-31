package com.tesco.spike.dao.impl;

import com.tesco.spike.dao.ClockResultDao;
import com.tesco.spike.vo.ClockResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;


@Repository
public class ClockResultJDBCDao implements ClockResultDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void createTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public String getClockResultByTransactioNo(String transactionNo) {
        return jdbcTemplate.queryForObject(
                "select clock_result from clockresult where transaction_no = ?",
                String.class, transactionNo);
    }

    public List<String> getClockResultByLoyaltyCardNo(String loyaltyCardNo) {
        String query = "select clock_result from clockresult where loyalty_card_no = ?";
        Object[] params = {loyaltyCardNo};

        return jdbcTemplate.queryForList(query, String.class, params);
    }

    @Override
    public void ingestClockResult(ClockResult clockResult) {
        String sql = "insert into clockresult(transaction_no, loyalty_card_no, clock_result) values " +
                "(?,?,?)";

        Object[] obj = {clockResult.getTransactionNo(), clockResult.getLoyaltyCardNo(), clockResult.getClockResultXmlString()};
        jdbcTemplate.update(sql, obj);
    }
}
