package com.damdeeng.webservice.common.service;


import com.damdeeng.webservice.utils.MailHandler;
import com.damdeeng.webservice.common.dto.MailDto;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;

@Service
@AllArgsConstructor
public class MailService {

//    @Value("${system.info.email}")
//    private String email;

    private JavaMailSender mailSender; // DI(=JavaMailSenderImpl)[MIME 지원]
    private static final String FROM_ADDRESS = "choihyunji1103@gmail.com";

    public void mailSend(MailDto mailDto) {

        try {

            // 스프링에서 제공하는 헬퍼 객체, HTML 레이아웃, 이미지 삽입, 첨부파일 등 MIME 메세지 가능
            MailHandler mailHandler = new MailHandler(mailSender);

            mailHandler.setTo(mailDto.getAddress());
            mailHandler.setFrom(MailService.FROM_ADDRESS);
            mailHandler.setSubject(mailDto.getTitle());

            // HTML Layout
            String htmlContent = "<p>" + mailDto.getMessage() + "메일 발송 테스트 입니다. ";
            mailHandler.setText(htmlContent, true);

            // 첨부 파일
            mailHandler.setAttach("newTest.txt", "static/file/sample1.rtf");

            // 이미지 삽입
            mailHandler.setInline("sample-img", "static/image/sample.JPG");

            mailHandler.send();

        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }

    }


}
