package hello.hellospring.domain;

import hello.hellospring.repository.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

//    @Autowired
//    PostsRepository postsRepository;

    @After
    public void cleanup() {
//        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        // BDD(Behaviour-Driven Development) 용어
        // BDD 프레임워크 -> Groovy기반 'Spock'
        // given, 테스트 기반 구축
//        postsRepository.save(Posts.builder()
//                .title("테스트 게시글")
//                .content("테스트 본문")
//                .author("choihyunji1103@gmail.com")
//                .build());

        // when, 테스트 행위 선언
//        List<Posts> postsList = postsRepository.findAll();

        // then, 테스트 검증 결과
//        Posts posts = postsList.get(0);
//        assertThat(posts.getTitle(), is("테스트 게시글"));
//        assertThat(posts.getContent(), is("테스트 본문"));

    }

    // BaseTimeEntity 등록


}
