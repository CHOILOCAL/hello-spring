package com.damdeeng.webservice.common.dto;


import com.damdeeng.webservice.test.domain.user.User;
import lombok.Getter;

@Getter
public class SessionUser {

    // 직렬화 기능을 가진 세션 Dto
    // 해당 클래스는 인증에 성공한 사용자만 이 생성한다.
    // 필요한 정보는 name, email, picture 정보 뿐

    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getEmail();
    }

}
