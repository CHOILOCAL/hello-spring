package com.damdeeng.webservice.test.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IndexControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void 메인페이지_로딩() {

        // when
        String body = this.restTemplate.getForObject("/index", String.class);

        // then
        // 첫번째 파라미터와, 두번째 파라미터 값 비교
        assertThat(body).contains("스프링부트로 시작하는 웹 서비스(mustache)");
        // 에러시 AssertionError -> 테스트 끝
        
        // 테스트 총 수행시간, 실행한 테스트의 수, 테스트 에러의 수, 테스트 실패의 수, 어떤 테스트 클래스 실행했는지
    }

}
