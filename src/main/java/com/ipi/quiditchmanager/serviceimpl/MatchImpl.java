package com.ipi.quiditchmanager.serviceimpl;

import com.ipi.quiditchmanager.dao.MatchDao;
import com.ipi.quiditchmanager.pojos.Game;
import com.ipi.quiditchmanager.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchImpl implements MatchService {
    @Autowired
    private MatchDao matchDao;
    @Override
    public Game addMatch(Game match) {
        return matchDao.save(match);
    }
}
