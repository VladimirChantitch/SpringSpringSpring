package com.ipi.quiditchmanager.service;

import com.ipi.quiditchmanager.pojos.Country;

import java.util.List;

public interface CountryService {
    Country addCountry(Country country);

    List<Country> getCountries();
}
