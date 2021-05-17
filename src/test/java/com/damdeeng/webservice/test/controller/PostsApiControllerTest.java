package com.damdeeng.webservice.test.controller;

import com.damdeeng.webservice.test.domain.posts.Posts;
import com.damdeeng.webservice.test.domain.posts.PostsRepository;
import com.damdeeng.webservice.test.dto.PostsSaveRequestDto;
import com.damdeeng.webservice.test.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostsApiControllerTest {

    // TEST 메소드 3조건
    // @Test 
    // public 접근자
    // 리턴이 void형

    // 특정한 테스트 소드의 실행 순서를 보장해주지 않는다

    // 데이터를 select해오는 과정에서 id값을 넣어주지 않는다면 ?
    // 1. null과 같은 특별한 을 리턴
    // 2. 해당하는 정보를 찾을 수 없다고 예외 (EmptyResultDataAccessException)

    // => 이럴경우 일부러 id가 없는 get()을 호출하고 2. 예외가 던져지는지 확인한다 (이것도 테스트 !)
    // assertThat으로는 테스트 불가, assertThat expected를 사용

    // 사고방식 *
    // 1. 테스트를 했을 경우 null을 리턴할 것이 아니라, 특정한 예외를 던져야 한다
    // 2. dao를 수정하는 대신 예외를 던지는 getUserFailure() 클래스를 생성 => 피피티
    // -> 테스트 코드를 설계문서처럼 만들기

    // Context는 생성할때 많은 자원을 소모하고 시간이 소요된다. -> 계속 생성할 필요 없음
    // @RunWith(SpringRunner.class)는 Context를 공유해준다


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    // 시작 전에 날릴 수도 있고, 실행 후에 날릴 수 있다
    @After
    public void tearDown() throws Exception {
        postsRepository.deleteAll();
    }

    // 만약 시작 전에 날리는 순서였다면,
    // 시작 전에 포스트를 select하면 count = 1이 될 것이고,
    // Delete를 한 후에 포스트를 select하면 count = 0이 될 것이다 -> 이것이 DELETE TEST
    // 이후 포스트를 등록하고 포스트를 select하면 count = 1이 될 것이다 -> 이것이 INSERT TEST
    
    // = 꼼꼼한 TEST 방식

    @Test
    public void Posts_등록된다() throws Exception {

        // 만약, tearDown()이 @Before이라면,
        // 만약, tearDown()이 @Before이라면,
        // assertThat(responseEntity.getCount()), is(0);

        // given
        String title = "title";
        String content = "content";
        String author = "author";

        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        // when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(content);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);

        // assertThat(responseEntity.getCount()), is(1);

    }

    @Test
    public void Posts_수정된다() throws Exception {

        // given
        Posts savedPosts = postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        Long updatedId = savedPosts.getId();

        String expectedTitle = "title2";
        String expectedContent = "content2";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updatedId;

        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        // when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);

    }

}
