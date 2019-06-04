package com.njwd.costsupplier;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.njwd.costsupplier")
public class CostsupplierApplication {

    public static void main(String[] args) {
        SpringApplication.run(CostsupplierApplication.class, args);
    }

}
