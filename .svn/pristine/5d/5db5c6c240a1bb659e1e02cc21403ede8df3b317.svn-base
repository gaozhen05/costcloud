package com.njwd.costconfig.service;

import com.njwd.costconfig.mapper.TestMapper;
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
