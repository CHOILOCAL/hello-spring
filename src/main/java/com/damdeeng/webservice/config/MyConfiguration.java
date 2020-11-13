package com.damdeeng.webservice.config;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;


//@Configuration
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class MyConfiguration {

    // 특정 자동구성 클래스 비활성화
    // 자동구성 클래스 excludeName 속성도 가능

}
