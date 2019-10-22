package com.ydd.boot.controller;

import com.ydd.boot.common.ResponVo;
import com.ydd.boot.model.User;
import com.ydd.boot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apis/UserController")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService ;

    @RequestMapping(value = "/add")
    public ResponVo add(@RequestBody User user){
        List<User> userList = userService.addUser(user);
        ResponVo vo = new ResponVo() ;
        vo.setFlag(true);
        vo.setData(userList);
        return vo ;
    }
    @RequestMapping(value = "/del")
    public ResponVo delete(@RequestParam Integer id){
        userService.deleteUserById(id);
        return ResponVo.success(true);
    }
    @RequestMapping(value = "/mod")
    public ResponVo mod(@RequestBody User user){
        userService.modUserById(user.getId(), user);
        return ResponVo.success(true);
    }

    @RequestMapping(value = "/select")
    public ResponVo select(@RequestBody User user){
        List<User> list = userService.select(user);
        log.info("userlist:{}", list);
        return ResponVo.success(list);
    }
}
