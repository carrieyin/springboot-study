package com.ydd.boot.service.impl;

import com.ydd.boot.model.User;
import com.ydd.boot.service.AAAService;
import com.ydd.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private AAAService aaaServcie ;
    @Override
    public void addUser(User user) {
        System.out.println("add user success ....");
        aaaServcie.addAAA("test");
    }
}
