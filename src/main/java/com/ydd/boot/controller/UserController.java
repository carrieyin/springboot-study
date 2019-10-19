package com.ydd.boot.controller;

import com.ydd.boot.common.ResponVo;
import com.ydd.boot.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/apis/userController")
@Slf4j
public class UserController {
    @RequestMapping(value = "/select")
    public ResponVo test(@RequestBody Map user){
        log.info("user : {}" , user);
        System.out.println(user);
        return ResponVo.success(user) ;
    }
}
