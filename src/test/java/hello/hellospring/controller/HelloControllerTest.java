package hello.hellospring.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class) // 테스트 실행지 JUnit에서 실행되는 내장된 실행자 외의 다른 실행자 실행 -> 'Spirng Runner'라는 스프링 실행자 실행 [스프링 부트와 JUnit 연결자]
@WebMvcTest(controllers = HelloController.class) // Web(Spring MVC)에 집중
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc; // 웹 API 시작점

    @Test
    public void hello가_리턴된다() throws Exception {

        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

}
