package com.njwd.costbill.controller;

import com.njwd.costbill.entity.ApiMessage;
import com.njwd.costbill.service.BillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api("costbill")
@RestController
@RequestMapping("bill/")
public class BillController {

    private  Logger logger = LoggerFactory.getLogger(BillController.class);

    @Autowired
    private BillService billService;

    @ApiOperation(value="基础测试", notes = "基本请求")
    @GetMapping("/test")
    public Object test(){
        return "bill";
    }

    @ApiOperation(value = "测试数据库连接",notes = "连接cost库")
    @GetMapping("/getEnterpriseInfo")
    public ApiMessage getEnterpriseInfo(String enterpriseId){

        Map<String,Object> map =  billService.getEnterpriseInfo(enterpriseId);
        return ApiMessage.success("ok",map);
    }
}
