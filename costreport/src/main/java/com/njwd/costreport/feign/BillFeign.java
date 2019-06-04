package com.njwd.costreport.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("COST-BILL")
public interface BillFeign {

    @RequestMapping(value = "bill/getEnterpriseInfo", method= RequestMethod.GET)
    Object getEnterpriseInfo(@RequestParam("enterpriseId") String enterpriseId) ;
}
