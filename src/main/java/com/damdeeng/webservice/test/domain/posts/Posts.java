package com.damdeeng.webservice.test.domain.posts;

import io.swagger.annotations.Info;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // lombok annotation
@NoArgsConstructor // lombok annotaion (2)
@Entity // jpa annotation
public class Posts {

    // 실제 db에 매칭될 클래스, Entity 클래스
    // ex) SalesManager.java => sales_manager table
    // Entity 클래스에선 절대 'setter' 생성을 안한다

    // 생성자를 통해 DB 주입
    // 값 변경시 필요 메소드 호출

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    // Builder 패턴
    // 해당 클래스의 builder 패턴 클래스 생성, 위에 필드의 클래스만 생성
    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

}
