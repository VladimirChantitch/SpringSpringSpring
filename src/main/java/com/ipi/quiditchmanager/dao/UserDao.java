package com.ipi.quiditchmanager.dao;

import com.ipi.quiditchmanager.pojos.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
