package com.epam.brest.task.dao;

import com.epam.brest.task.domain.Bet;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.joda.time.LocalDateTime;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.Assert;

/**
 * Created by sphincs on 2.12.14.
 */
public class BetDaoImpl implements BetDao {

    public static final String SINGLEBETID = "singlebetid";
    public static final String BETDATE = "betdate";
    public static final String CLIENTNAME = "clientname";
    public static final String PASSPORT = "passport";
    public static final String FACTOR = "factor";
    public static final String BETMONEY = "betmoney";
    public static final String MARKER = "marker";
    public static final String PROCESSED = "processed";



    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private KeyHolder keyHolder = new GeneratedKeyHolder();
    private static final Logger LOGGER = LogManager.getLogger();

    public void setDataSource(DataSource dataSource) {
        namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(dataSource);
    }

    public class BetMapper implements RowMapper<Bet> {
        @Override
        public Bet mapRow(ResultSet rs, int i) throws SQLException {
            Bet bet = new Bet();
            bet.setSingleBetId(rs.getLong(SINGLEBETID));
            bet.setBetDate(new LocalDateTime(rs.getTimestamp(BETDATE)));
            bet.setClientName(rs.getString(CLIENTNAME));
            bet.setPassport(rs.getString(PASSPORT));
            bet.setFactor(rs.getDouble(FACTOR));
            bet.setBetMoney(rs.getLong(BETMONEY));
            bet.setMarker(rs.getBoolean(MARKER));
            bet.setProcessed(rs.getBoolean(PROCESSED));
            return bet;
        }
    }

    @Override
    public Long addBet(Bet bet) {

        LOGGER.debug("add bet({}) ", bet);

        Assert.notNull(bet);
        Assert.isNull(bet.getSingleBetId());
        Assert.notNull(bet.getBetDate(), "Bet date should be specified.");
        Assert.notNull(bet.getClientName(), "Client name should be specified.");
        Assert.notNull(bet.getPassport(), "Client passport should be specified.");
        Assert.notNull(bet.getFactor(), "Factor should be specified.");
        Assert.notNull(bet.getBetMoney(), "Bet money should be specified.");
        Assert.notNull(bet.isMarker(), "Marker should be specified.");
        Assert.notNull(bet.isProcessed(), "Processed should be specified.");

        KeyHolder keyholder = new GeneratedKeyHolder();

        SqlParameterSource namedParameters;
        namedParameters = new MapSqlParameterSource()
                .addValue(SINGLEBETID, bet.getSingleBetId())
                .addValue(BETDATE, bet.getBetDate())
                .addValue(CLIENTNAME, bet.getClientName())
                .addValue(PASSPORT, bet.getPassport())
                .addValue(FACTOR, bet.getFactor())
                .addValue(BETMONEY, bet.getBetMoney())
                .addValue(MARKER, bet.isMarker())
                .addValue(PROCESSED, bet.isProcessed());

        namedParameterJdbcTemplate.update("insert into BET(SINGLEBETID, BETDATE, CLIENTNAME, " +
                "PASSPORT, FACTOR, BETMONEY, MARKER, PROCESSED) values (:SINGLEBETID, :BETDATE, " +
                ":CLIENTNAME, :PASSPORT, :FACTOR, :BETMONEY, :MARKER, :PROCESSED)",
                namedParameters, keyholder);

                LOGGER.debug("add bet(): id{}",keyholder.getKey());

        return keyholder.getKey().longValue();
    }

    @Override
    public void removeBet(Long singleBetId) {

        LOGGER.debug("remove bet {}", singleBetId);

        Map<String, Object> parameters = new HashMap(1);
        parameters.put(SINGLEBETID, singleBetId);

        namedParameterJdbcTemplate.update("delete from BET where SINGLEBETID = :SINGLEBETID", parameters);
    }

    @Override
    public Bet getBetById(Long singleBetId) {

        LOGGER.debug("get bet by id {}", singleBetId);

        Map<String, Object> parameters = new HashMap(1);
        parameters.put(SINGLEBETID, singleBetId);

        return namedParameterJdbcTemplate.queryForObject("select SINGLEBETID, BETDATE, " +
                        "CLIENTNAME, PASSPORT, FACTOR, BETMONEY, MARKER, PROCESSED" +
                        "from BET where SINGLEBETID = :SINGLEBETID",
                        parameters,new BetMapper());
    }

    @Override
    public Bet getBetByDate(LocalDateTime firstDate) {

        LOGGER.debug("get bet by date{}", firstDate);

        Map<String, Object> parameters = new HashMap(1);
        parameters.put(BETDATE, firstDate);

        return namedParameterJdbcTemplate.queryForObject("select SINGLEBETID, BETDATE, " +
                        "CLIENTNAME, PASSPORT, FACTOR, BETMONEY, MARKER, PROCESSED" +
                        "from BET where BETDATE = :BETDATE",
                        parameters,new BetMapper());
    }

    @Override
    public Bet getBetByDateInterval(LocalDateTime firstDate, LocalDateTime secondDate) {
        return null;
    }

    @Override
    public List<Bet> getAllBets() {

        LOGGER.debug("get all bets {}");

        return namedParameterJdbcTemplate.query("select SINGLEBETID, BETDATE, CLIENTNAME, " +
                "PASSPORT, FACTOR, BETMONEY, MARKER, PROCESSED from BET", new BetMapper());
    }

    @Override
    public void updateBet(Bet bet) {

        LOGGER.debug("update bet {}", bet);

        Map<String, Object> parameters = new HashMap(8);

        parameters.put(SINGLEBETID, bet.getSingleBetId());
        parameters.put(BETDATE, bet.getBetDate());
        parameters.put(CLIENTNAME, bet.getClientName());
        parameters.put(PASSPORT, bet.getPassport());
        parameters.put(FACTOR, bet.getFactor());
        parameters.put(BETMONEY, bet.getBetMoney());
        parameters.put(MARKER, bet.isMarker());
        parameters.put(PROCESSED, bet.isProcessed());
        namedParameterJdbcTemplate.update("update bet set BETDATE = :BETDATE, " +
                "CLIENTNAME = :CLIENTNAME, PASSPORT = :PASSPORT, FACTOR = :FACTOR, " +
                "BETMONEY = :BETMONEY, MARKER = :MARKER, PROCESSED = :PROCESSED " +
                "where SINGLEBETID = :SINGLEBETID", parameters);
    }


}
