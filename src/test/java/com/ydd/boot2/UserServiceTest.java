package com.ydd.boot2;

import com.ydd.boot.MyApplication;
import com.ydd.boot.service.AAAService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyApplication.class)
public class UserServiceTest {
    @Autowired
    private AAAService aaaServcie  ;
    @Test
    public void testAddUser(){
        aaaServcie.addAAA("hello");
    }

}
