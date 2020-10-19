package hello.hellospring.utils;

import com.sun.el.parser.Token;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class JwtTokenInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        String header = request.getHeader(AuthConstants.AUTH_HEADER);

        if (header != null) {
            String token = TokenUtils.getTokenFromHeader(header);
            if (TokenUtils.isValidToken(token)) {
                return true;
            }
        }

        response.sendRedirect("/error/unauthorized");
        return false;
    }

}
