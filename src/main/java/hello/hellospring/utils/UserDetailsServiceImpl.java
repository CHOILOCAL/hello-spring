package hello.hellospring.utils;

import hello.hellospring.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    // UserRepository로부터 조회한 결과를 Optional로 반환 -> map함수를 이용하여 새로운 UserDetailsVO 객체 생성/반환
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // UserDetailsService로부터 아이디를 기반으로 데이터 조회
        return userRepository.findByUserEmail(userEmail).map(
                u -> new UserDetailsVO(u, Collections.singleton(new SimpleGrantedAuthority(u.getRole().getValue())))).orElseThrow(
                        () -> new UserNotFoundException(userEmail));
    }

}
