package com.njwd.costreport.service;

import com.njwd.costreport.mapper.TestMapper;
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
