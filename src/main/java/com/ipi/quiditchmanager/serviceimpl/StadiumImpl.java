package com.ipi.quiditchmanager.serviceimpl;

import com.ipi.quiditchmanager.dao.StadiumDao;
import com.ipi.quiditchmanager.pojos.Stadium;
import com.ipi.quiditchmanager.service.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StadiumImpl implements StadiumService {
    @Autowired
    private StadiumDao stadiumDao;
    @Override
    public Stadium addStadium(Stadium stadium) {
        return stadiumDao.save(stadium);
    }
    @Override
    public List<Stadium> getStadium() {
        return stadiumDao.findAll();
    }
    @Override
    public Stadium findStadiumByName(String name) {
        return stadiumDao.findByName(name);
    }
}
