package com.damdeeng.webservice.common.controller;

import com.damdeeng.webservice.common.service.MainService;
import com.damdeeng.webservice.utils.JwtResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "메인 테스트")
@RequestMapping("/v1/api/main")
public class MainController {

    @Autowired
    private MainService service;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String main() {
        return "main";
    }



}
