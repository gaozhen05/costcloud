package com.njwd.stockphoto.service;

import com.njwd.stockphoto.mapper.StockMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class StockService {

    @Resource
    private StockMapper stockMapper;

    public Map<String, Object> getStockInfo(String id) {
        return stockMapper.getStockInfo(id);
    }

    public List<Map<String, Object>> getStockList() {
        return stockMapper.getStockList();
    }
}
