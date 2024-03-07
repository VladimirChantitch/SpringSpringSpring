package com.ipi.quiditchmanager.serviceimpl;

import com.ipi.quiditchmanager.dao.CountryDao;
import com.ipi.quiditchmanager.pojos.Country;
import com.ipi.quiditchmanager.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryImpl implements CountryService {
    @Autowired
    private CountryDao countryDao;
    @Override
    public Country addCountry(Country country) {
        return  countryDao.save(country);
    }
}
