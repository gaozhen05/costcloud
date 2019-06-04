package com.njwd.costconfig.controller;

import com.njwd.costconfig.entity.ApiMessage;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("登录与注册")
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/login")
    public ApiMessage login(){

        stringRedisTemplate.opsForValue().set("22","bb");

        return ApiMessage.success("ok","bb");
    }

}
