package com.damdeeng.webservice.common.service;


import com.damdeeng.webservice.common.dto.OAuth2Attributes;
import com.damdeeng.webservice.common.dto.SessionUser;
import com.damdeeng.webservice.test.domain.user.User;
import com.damdeeng.webservice.test.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService<OAuth2UserRequest, OAuth2User> deletgate = new DefaultOAuth2UserService();

        OAuth2User oAuth2User = deletgate.loadUser(userRequest);

        // 현재 로그인 진행중인 API 구분 ex) naver, google, kakao ...
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        // 로그인시 진행되는 key 필드값, 구글의 경우 'sub', kakao와 naver는 값이 없음
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        // 로그인 유저 정보를 담을 클래스
        OAuth2Attributes attributes = OAuth2Attributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        // 구글의 사용자 정보가 업데이트 됐을때 대비하여 UPDATE 기능 같이 구현
        // 구글 사용자의 이름이나 프로필 사진이 변경될경우 User 엔티티에도 같이 반영
        User user = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(user)); // 세션에 사용자 정보를 담기 위한 dto 클래스

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(), attributes.getNameAttributeKey());
    };

    private User saveOrUpdate(OAuth2Attributes attributes) {

        User user = userRepository.findByEmail(attributes.getEmail()).map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
        .orElse(attributes.toEntity());

        return userRepository.save(user);
    }



}
