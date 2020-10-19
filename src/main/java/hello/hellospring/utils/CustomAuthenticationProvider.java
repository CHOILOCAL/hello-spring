package hello.hellospring.utils;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;


@RequiredArgsConstructor
@Log4j2
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Resource(name = "userDetailsService")
    private UserDetailsService userDetailsService; // 일치시 토큰 가져옴

    @NonNull
    private BCryptPasswordEncoder passwordEncoder; // 패스워드 불일치 에러처리, 인코딩/디코딩을 통한 패스워드 매칭

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;

        // AuthenticaionFilter에서 생성된 토큰으로부터 아이디 조회
        String userEmail = token.getName();
        String userPw = (String) token.getCredentials();

        // UserDetailsService를 통해 DB에서 사용자 조회
        UserDetailsVO userDetailsVO = (UserDetailsVO) userDetailsService.loadUserByUsername(userEmail);

        if (!passwordEncoder.matches(userPw, userDetailsVO.getPassword())) {
            throw new BadCredentialsException(userDetailsVO.getUsername() + "Invalid Password");
        }

        return new UsernamePasswordAuthenticationToken(userDetailsVO, userPw, userDetailsVO.getAuthorities());

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
