package com.ipi.quiditchmanager.dao;

import com.ipi.quiditchmanager.pojos.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamDao extends JpaRepository<Team, Long> {
    Team findByName(String name);
}
