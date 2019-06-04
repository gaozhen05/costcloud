package com.njwd.costprocess.controller;

import com.njwd.costprocess.entity.ApiMessage;
import com.njwd.costprocess.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api("costtest")
@RestController
@RequestMapping("test/")
public class TestController {

    private  Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestService testService;

    @ApiOperation(value="基础测试", notes = "基本请求")
    @GetMapping("/test")
    public Object test(){
        return "process";
    }

    @ApiOperation(value = "测试数据库连接",notes = "连接cost库")
    @GetMapping("/getEnterpriseInfo")
    public ApiMessage getEnterpriseInfo(String enterpriseId){

        Map<String,Object> map =  testService.getEnterpriseInfo(enterpriseId);
        return ApiMessage.success("ok",map);
    }
}
