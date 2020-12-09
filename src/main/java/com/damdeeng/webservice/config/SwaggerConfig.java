package com.damdeeng.webservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private String version;
    private String title;

    /// 두개의 스펙 존재, 구분(groupName)
    @Bean
    public Docket apiV1() {

        version = "V1";
        title = "DamDeeng Tour.com " + version;

        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .groupName(version)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/v1/api/**"))
                .build()
                .apiInfo(apiInfo(title, version));
    }

    @Bean
    public Docket apiV2() {

        version = "V2";
        title = "DamDeeng Tour " + version;

        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .groupName(version)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/v2/api/**"))
                .build()
                .apiInfo(apiInfo(title, version));
    }

    private ApiInfo apiInfo(String title, String version) {

        return new ApiInfo(
                title,
                "Swagger API Docs by ChoiLocal",
                version,
                "www.example.com",
                new Contact("Contact Me", "www.example.com", "choihyunji1103@gmail.com"),
                "Licenses",
                "www.example.com",
                new ArrayList<>()
        );

    }

}
