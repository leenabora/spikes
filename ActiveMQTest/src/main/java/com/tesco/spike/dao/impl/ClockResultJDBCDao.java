package com.tesco.spike.dao.impl;

import com.tesco.spike.dao.ClockResultDao;
import com.tesco.spike.vo.ClockResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;


@Repository
public class ClockResultJDBCDao implements ClockResultDao {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate template;

    @Autowired
    public void createTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        template = new NamedParameterJdbcTemplate(dataSource);
    }

    public String getClockResultByTransactioNo(String transactionNo) {
        String query = "SELECT clock_result FROM clockresult WHERE transaction_no = :transactionNo";

        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("transactionNo", transactionNo, Types.VARCHAR);

        return (String) template.queryForObject(query, paramSource, String.class);
    }

   public List<String> getClockResultByLoyaltyCardNo(String loyaltyCardNo) {
        String query = "SELECT clock_result FROM clockresult WHERE loyalty_card_no = :loyaltyCardNo";

        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("loyaltyCardNo", loyaltyCardNo, Types.VARCHAR);

        return  template.queryForList(query, paramSource, String.class);
    }


    @Override
    public void ingestClockResult(ClockResult clockResult) {
        String query = "INSERT INTO clockresult(transaction_no, loyalty_card_no, clock_result) VALUES (:transactionNo,:loyaltyCardNo,:clockResult)";

        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("transactionNo", clockResult.getTransactionNo(), Types.VARCHAR);
        paramSource.addValue("loyaltyCardNo", clockResult.getLoyaltyCardNo(), Types.VARCHAR);
        paramSource.addValue("clockResult", clockResult.getClockResultString(), Types.CLOB);

        template.update(query, paramSource);
    }
}
