package com.damdeeng.webservice.common.dto;

import com.damdeeng.webservice.test.domain.user.Role;
import com.damdeeng.webservice.test.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuth2Attributes {

    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuth2Attributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    // oAuth2User 에서 반환하는 사용자 정보는 Map이기에 값 하나하나씩 반환할 필요가 있음
    public static OAuth2Attributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuth2Attributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {

        return OAuth2Attributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    // Entity가 처음 생성되는 시점 = API를 통해 처음 가입하는 시점
    // 가입하면 'GUEST' 권한 부여
    // OAuth2Attributes 클래스 생성이 끝나면 같은 패키지에 SessionUser 클래스를 생성한다
    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST)
                .build();
    }

}
