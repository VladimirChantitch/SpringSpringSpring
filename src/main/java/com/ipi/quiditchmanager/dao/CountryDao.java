package com.ipi.quiditchmanager.dao;

import com.ipi.quiditchmanager.pojos.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryDao extends JpaRepository<Country, Long> {
    Country findByName(String name);
}
