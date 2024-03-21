package com.ipi.quiditchmanager.service;

import com.ipi.quiditchmanager.pojos.Country;

import java.util.List;

public interface CountryService {
    Country addCountry(Country country);

    List<Country> getCountries();

    void deleteById(Long id);

    Country create();

    void updateCountry(Long id, String countryName);

    void updateCountry(Country france);
}
