package hello.hellospring.utils;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRole {

    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private String value;
}
