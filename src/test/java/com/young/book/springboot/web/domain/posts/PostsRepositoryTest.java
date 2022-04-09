package com.young.book.springboot.web.domain.posts;

import com.young.book.springboot.domain.posts.Posts;
import com.young.book.springboot.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest // H2 데이터베이스 자동 실행
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After // 단위 테스트가 끝날 때마다 수행되는 메서드
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void test(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder() // .save = 테이블 posts에 insert/update 쿼리 실행 (id 값이 존재하면 update, 없으면 insert)
                .title(title)
                .content(content)
                .author("young@naver.com")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll(); // .findAll = 테이블 posts 모든 데이터 조회

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

}
