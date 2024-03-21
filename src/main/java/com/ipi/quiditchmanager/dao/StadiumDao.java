package com.ipi.quiditchmanager.dao;

import com.ipi.quiditchmanager.pojos.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StadiumDao extends JpaRepository<Stadium, Long> {
    Stadium findByName(String name);
            List<Stadium> findAll();
}
