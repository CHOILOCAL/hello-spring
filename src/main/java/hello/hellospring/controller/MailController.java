package hello.hellospring.controller;

import hello.hellospring.dto.MailDto;
import hello.hellospring.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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