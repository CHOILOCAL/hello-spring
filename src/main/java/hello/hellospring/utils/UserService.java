package hello.hellospring.utils;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserVO signUp(UserVO userVO);

    Optional<UserVO> findByEmail(String userEmail);

    List<UserVO> findAll();

}
