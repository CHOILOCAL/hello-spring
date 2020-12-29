package com.damdeeng.webservice.test.service;

import com.damdeeng.webservice.test.domain.posts.Posts;
import com.damdeeng.webservice.test.domain.posts.PostsRepository;
import com.damdeeng.webservice.test.dto.PostsListReponseDto;
import com.damdeeng.webservice.test.dto.PostsResponseDto;
import com.damdeeng.webservice.test.dto.PostsSaveRequestDto;
import com.damdeeng.webservice.test.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {

        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListReponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListReponseDto::new)
                .collect(Collectors.toList());
    }

    public void delete (Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습ㄴ디ㅏ. id=" + id));

        // deleteById method로 id로 삭제하는 방법 - (1)
        // 존재하는 Posts인지 확인 후 entity로 날리는 방법 - (2)
        postsRepository.delete(posts);
    }

}
