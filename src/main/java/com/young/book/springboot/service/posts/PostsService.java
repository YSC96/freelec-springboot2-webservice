package com.young.book.springboot.service.posts;

import com.young.book.springboot.domain.posts.Posts;
import com.young.book.springboot.domain.posts.PostsRepository;
import com.young.book.springboot.web.dto.PostsListResponseDto;
import com.young.book.springboot.web.dto.PostsResponseDto;
import com.young.book.springboot.web.dto.PostsSaveRequestDto;
import com.young.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional(readOnly = true) // 트랜잭션 범위는 유지하되, 조회 기능만 남겨둠으로써 조회 속도가 개선됨.
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
        // stream 이란 ?
        // Java8 부터 지원하는 Stream은 컬렉션, 배열등에 대해 저장되어있는 요소들을 하나씩 참조하며 반복적인 처리를 가능케하는 기능. 불필요한 for문을 쓰지않고도 깔끔한 코드로 변형 가능.
        // postsRepository 결과로 넘어온 Posts의 Stream 을 map 을 통해  new PostsListResponseDto 에 매핑. (.map(posts -> new PostsListResponseDto(posts)))
        // collect를 사용해서 List로 변환.
    }

    @Transactional
    public void delete (Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id ));
        postsRepository.delete(posts);
    }

}
