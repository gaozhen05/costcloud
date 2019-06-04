package com.njwd.stockphoto;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableFeignClients
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.njwd.stockphoto")
public class PhotostockApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhotostockApplication.class, args);
    }

}
