package com.damdeeng.webservice.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

//    @GetMapping("/api/hello")
//    public String hello(Model model) {
//        model.addAttribute("data", "최현지");
//        return "hello2";
//    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

}
