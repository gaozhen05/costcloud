package com.njwd.stockphoto.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.njwd.stockphoto.entity.ApiMessage;
import com.njwd.stockphoto.feign.BillFeign;
import com.njwd.stockphoto.service.StockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stock")
@Api("swaggerTest")
public class StockController {

    private Logger logger = LoggerFactory.getLogger(StockController.class);

    @Autowired
    private StockService stockService;

    @Autowired
    private BillFeign billFeign;


    @ApiOperation(value = "测试项目启动", notes = "基本请求")
    @GetMapping("/test")
    public ApiMessage test() throws Exception{
        logger.info("测试异常");
//        int i = 1/0;
        return ApiMessage.success("ok","stock");
    }

    @ApiOperation(value = "测试数据库连接",notes = "连接stock库")
    @GetMapping("/getStockInfo")
    public ApiMessage getStockInfo(String id){
        Map<String,Object> map =  stockService.getStockInfo(id);
        return ApiMessage.success("ok",map);
    }

    @ApiOperation(value = "测试分页插件",notes = "测试分页插件")
    @GetMapping("/getStockList")
    public ApiMessage getStockList(int pageNum, int pageSize){
        //1、设置分页信息，包括当前页数和每页显示的总计数
        PageHelper.startPage(pageNum - 1, pageSize);
        List<Map<String,Object>> list = stockService.getStockList();

        //3、获取分页查询后的数据
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(list);
        return ApiMessage.success("ok",pageInfo);
    }

    @ApiOperation(value = "测试feign",notes = "调取bill接口")
    @GetMapping("/testFeign")
    public ApiMessage testFeign(String enterpriseId){
        Object enterpriseInfo = billFeign.getEnterpriseInfo(enterpriseId);
        return ApiMessage.success("ok",enterpriseInfo);
    }
}
