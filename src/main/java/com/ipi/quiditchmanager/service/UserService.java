package com.ipi.quiditchmanager.service;

import com.ipi.quiditchmanager.pojos.User;

public interface UserService {
    User addUser(User user);

    User authenticateUser(String username, String password);
}
