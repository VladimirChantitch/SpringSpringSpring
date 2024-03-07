package com.ipi.quiditchmanager.serviceimpl;

import com.ipi.quiditchmanager.dao.UserDao;
import com.ipi.quiditchmanager.pojos.User;
import com.ipi.quiditchmanager.service.ChampionshipService;
import com.ipi.quiditchmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User addUser(User user) {
        return userDao.save(user);
    }

    @Override
    public User authenticateUser(String username, String password) {
        User user = userDao.findByUsername(username);
        if (user != null)
            if (user.getPassword().equals(password.trim()))
                return user;
        return null;
    }
}
