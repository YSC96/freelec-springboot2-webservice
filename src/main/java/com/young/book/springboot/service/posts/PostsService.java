package com.young.book.springboot.service.posts;

import com.young.book.springboot.domain.posts.Posts;
import com.young.book.springboot.domain.posts.PostsRepository;
import com.young.book.springboot.web.dto.PostsResponseDto;
import com.young.book.springboot.web.dto.PostsSaveRequestDto;
import com.young.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor // final이 선언된 모든 필드를 인자값으로 하는 생성자
@Service
public class PostsService {

    // Autowired 로 Bean을 주입받는 방식보다 RequiredArgsConstructor 를 통해 생성자를 주입받는 방식을 권장함.
    private final PostsRepository postsRepository;

    @Transactional // 예외 발생 시 rollback 처리
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));
        posts.update(requestDto.getTitle(),requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        return new PostsResponseDto(entity);

    }
}
