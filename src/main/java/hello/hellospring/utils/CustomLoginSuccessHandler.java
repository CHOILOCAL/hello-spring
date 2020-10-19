package hello.hellospring.utils;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        UserVO userVO = ((UserDetailsVO) authentication.getPrincipal()).getUserVO();
        String token = TokenUtils.generateJwtToken(userVO); // 토큰 발급

        response.addHeader(Authentication.AUTH_HEADER, AuthConstants.TOKEN_TYPE + "" + token); // 토큰 추가 후 반환 -> /user/loginSuccess redirect
    }

}
