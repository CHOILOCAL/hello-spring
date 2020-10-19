package hello.hellospring.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.hellospring.exception.InputNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    // 아이디와 패스워드 유효성 검사
    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    // 인증 시도
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        UsernamePasswordAuthenticationToken authRequest;

        try {

            UserVO userVO = new ObjectMapper().readValue(request.getInputStream(), UserVO.class);
            authRequest = new UsernamePasswordAuthenticationToken(userVO.getUserEmail(), userVO.getUserPw()); // email, password 객체 생성

        } catch (IOException e) {
            throw new InputNotFoundException(); // 아이디와 비밀번호가 제대로 전달되지 않은 경우
        }

        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

}
