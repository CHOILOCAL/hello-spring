package com.damdeeng.webservice.test.controller;


import com.damdeeng.webservice.test.dto.PostsResponseDto;
import com.damdeeng.webservice.test.dto.PostsSaveRequestDto;
import com.damdeeng.webservice.test.dto.PostsUpdateRequestDto;
import com.damdeeng.webservice.test.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    // 저장
    @PostMapping(value = "/api/vi/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }
    
    // 수정
    @PostMapping(value = "/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    // 조회
    @GetMapping(value = "/api/v1/posts/{id}")
    public PostsResponseDto findById (@PathVariable Long id) {
        return postsService.findById(id);
    }
}
