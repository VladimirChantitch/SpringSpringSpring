package com.ipi.quiditchmanager.serviceimpl;

import com.ipi.quiditchmanager.dao.ChampionshipDao;
import com.ipi.quiditchmanager.dao.CountryDao;
import com.ipi.quiditchmanager.pojos.ChampionShip;
import com.ipi.quiditchmanager.pojos.Country;
import com.ipi.quiditchmanager.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryImpl implements CountryService {
    @Autowired
    private CountryDao countryDao;
    @Autowired
    private ChampionshipDao championshipDao;
    @Override
    public Country addCountry(Country country) {
        return  countryDao.save(country);
    }

    @Override
    public List<Country> getCountries() {
        return countryDao.findAll();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Country create() {
        Country country = countryDao.findByName("Country Name");
        if (country != null)
            return country;

        country = new Country();
        country.setName("Country Name");
        countryDao.save(country);
        return countryDao.findByName("Country Name");
    }

    @Override
    public void updateCountry(Long id, String countryName) {
        Country country = countryDao.findById(id).get();
        country.setName((countryName));
        countryDao.save(country);
    }
}
