package com.damdeeng.webservice.test.controller;

import com.damdeeng.webservice.test.dto.PostsSaveRequestDto;
import com.damdeeng.webservice.test.repository.PostsRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class WebRestController {

    private final PostsRepository postsRepository;

    @PostMapping(value = "/posts")
    public void savePosts(@RequestBody PostsSaveRequestDto dto) {
//        posts.setCreateDate(new LocalDate()); // 생성시간, 수정시간 자동화 JPA Auditing
        postsRepository.save(dto.toEntity());
    }
}
