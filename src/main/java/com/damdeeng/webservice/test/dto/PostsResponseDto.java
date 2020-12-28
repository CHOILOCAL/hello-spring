package com.damdeeng.webservice.test.dto;

import com.damdeeng.webservice.test.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    // ReponseDto는 entity의 일부만 사용, 생성자로 Entity를 받아 필드에 값을 넣는다
    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();

    }
}
