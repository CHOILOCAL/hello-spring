package hello.hellospring.controller;


import hello.hellospring.utils.UserRole;
import hello.hellospring.utils.UserService;
import hello.hellospring.utils.UserVO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/user")
@Log4j2
public class UserController {

//    private BCryptPasswordEncoder passwordEncoder;
//    private UserService userService;

//    public ResponseEntity signUp(@RequestBody UserVO userVO) {

//        if (!userService.findByEmail(userVO.getUserEmail()).isPresent()) {
//            userVO.setUserPw(passwordEncoder.encode(userVO.getUserPw()));
//            userVO.setRole(UserRole.USER);
//            userService.signUp(userVO);
//            log.debug("Sign Up Complete");
//            return ResponseEntity.ok().build();
//
//        }
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//    }


//    @GetMapping(value = "/findAll")
//    public ResponseEntity findAll() {

//        return ResponseEntity.ok(userService.findAll());

//    }

}
