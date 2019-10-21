package com.ydd.boot.service.impl;

import com.ydd.boot.service.AAAService;
import org.springframework.stereotype.Service;

@Service
public class AAAServiceImpl implements AAAService {
    @Override
    public void addAAA(String info) {
        System.out.println("aaa busi success");
    }
}
