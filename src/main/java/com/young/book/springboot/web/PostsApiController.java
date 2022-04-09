package com.young.book.springboot.web;

import com.young.book.springboot.service.posts.PostsService;
import com.young.book.springboot.web.dto.PostsResponseDto;
import com.young.book.springboot.web.dto.PostsSaveRequestDto;
import com.young.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){ //  PathVariable : URL 경로에 변수를 넣어줌
        return postsService.update(id, requestDto);
    }


    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById (@PathVariable Long id){ // 조회
        return postsService.findById(id);
    }

}
