package com.damdeeng.webservice.common.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class TestController {

    @PostMapping("/api/ip")
    public ResponseEntity<String> ip (HttpServletRequest request) {
        return ResponseEntity.ok(request.getRemoteAddr());
    }
}
