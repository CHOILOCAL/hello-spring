package hello.hellospring.exception;

public class UserNotFoundException extends RuntimeException {

    // 사용자의 아이디를 기반으로 데이터가 조회되지 않았을 경우 처리
    public UserNotFoundException(String userEmail){
        super(userEmail + " NotFoundException");
    }

}
