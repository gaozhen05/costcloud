package com.njwd.costprocess.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface TestMapper {
    Map<String, Object> getEnterpriseInfo(@Param("enterpriseId") String enterpriseId);
}
