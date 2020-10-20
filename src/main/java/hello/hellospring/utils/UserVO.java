package hello.hellospring.utils;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "USER")
@Getter
public class UserVO extends CommonVO implements Serializable {

    @Column(nullable = false, unique = true, length = 50)
    private String userEmail;

    @Setter
    private String userPw;

    @Setter
    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private UserRole role;

}
