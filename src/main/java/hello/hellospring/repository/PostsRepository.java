package hello.hellospring.repository;

import hello.hellospring.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    // ibatis/mybatis = DAO = DB Layer
    // JPA = Repository = DB Layer



}
