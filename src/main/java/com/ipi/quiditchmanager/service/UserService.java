package com.ipi.quiditchmanager.service;

import com.ipi.quiditchmanager.pojos.User;
import jakarta.servlet.http.HttpSession;

public interface UserService {
    User addUser(User user);

    User authenticateUser(String username, String password);

    boolean getIsLogged(HttpSession session);
}
