package com.damdeeng.webservice.common.controller;

import com.damdeeng.webservice.common.dto.MailDto;
import com.damdeeng.webservice.common.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class MailController {

    private final MailService mailService;

    @GetMapping("mail")
    public String dispMail() {
        return "mail";
    }

    @PostMapping("mail")
    public void execMail(MailDto mailDto) {
        mailService.mailSend(mailDto);
    }

}
