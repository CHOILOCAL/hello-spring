package com.damdeeng.webservice.test.controller;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Api(value = "SwaggerControllerTest V1")
@RequestMapping("/v1/api")
public class SwaggerControllerTest {

    @ApiOperation(value = "Test", notes = "Swagger API 예제")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 400, message = "fail"),
            @ApiResponse(code = 500, message = "error")
    })
    @GetMapping(value = "/test")
    public Map<String, String> selectTestCode(
            @ApiParam(value = "번호", required = true, example = "1")
            @RequestParam String no) {

        Map<String, String> result = new HashMap<>();
        result.put("name", "choilocal");
        result.put("age", "24");

        return result;
    }

}
