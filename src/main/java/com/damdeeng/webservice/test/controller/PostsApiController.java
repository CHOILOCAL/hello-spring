package com.damdeeng.webservice.test.controller;


import com.damdeeng.webservice.test.dto.PostsListReponseDto;
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

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

//    @GetMapping("/api/v1/posts/list")
//    public List<PostsListResponseDto> findAll() {
//        return postsService.findAllDesc();
//    }
}
