package com.njwd.costbill.service;

import com.njwd.costbill.mapper.BillMapper;
import com.njwd.costbill.mapper.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class TestService {

    @Resource
    private TestMapper testMapper;

    public Map<String, Object> getEnterpriseInfo(String enterpriseId) {
        return testMapper.getEnterpriseInfo(enterpriseId);
    }
}
