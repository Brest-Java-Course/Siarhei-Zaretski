package com.epam.brest.task.dao;

import com.epam.brest.task.domain.Bet;

import org.joda.time.LocalDateTime;
import java.util.List;

/**
 * Created by sphincs on 2.12.14.
 */

public interface BetDao {

    public Long addBet(Bet bet);

    public void removeBet(Long singleBetId);

    public Bet getBetById(Long singleBetId);

    public Bet getBetByDate(LocalDateTime firstDate);

    public Bet getBetByDateInterval(LocalDateTime firstDate, LocalDateTime secondDate);

    public List<Bet> getAllBets();

    public void updateBet(Bet bet);






}
