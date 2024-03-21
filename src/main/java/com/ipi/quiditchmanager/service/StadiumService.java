package com.ipi.quiditchmanager.service;

import com.ipi.quiditchmanager.pojos.Stadium;

import java.util.List;

public interface StadiumService {
    Stadium addStadium(Stadium stadium);

    List<Stadium> getStadium();

    Stadium findStadiumByName(String name);
}
