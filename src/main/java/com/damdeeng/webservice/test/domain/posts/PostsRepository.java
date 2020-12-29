package com.damdeeng.webservice.test.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long>{

    // extends JpaRepository<Entity Type, PK Type>
    // CRUD 메소드 자동생성
    // 주의 : Entity Class와 Entity Repository 같은 위치 <도메인 패키지>

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();

}
