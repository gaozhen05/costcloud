package com.njwd.costbill.service;

import com.njwd.costbill.mapper.BillMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class BillService {

    @Resource
    private BillMapper billMapper;

    public Map<String, Object> getEnterpriseInfo(String enterpriseId) {
        return billMapper.getEnterpriseInfo(enterpriseId);
    }
}
