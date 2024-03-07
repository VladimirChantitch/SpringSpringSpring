package com.ipi.quiditchmanager.serviceimpl;

import com.ipi.quiditchmanager.dao.StadiumDao;
import com.ipi.quiditchmanager.pojos.Stadium;
import com.ipi.quiditchmanager.service.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StadiumImpl implements StadiumService {
    @Autowired
    private StadiumDao stadiumDao;
    @Override
    public Stadium addStadium(Stadium stadium) {
        return stadiumDao.save(stadium);
    }
}
