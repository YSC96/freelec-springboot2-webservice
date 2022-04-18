package com.young.book.springboot.web;

import com.young.book.springboot.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        return "index"; // src/main/resources/templates/ + .mustache 로 전환되어 ViewResolver가 처리
    }


    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }



}
