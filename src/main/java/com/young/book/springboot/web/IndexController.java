package com.young.book.springboot.web;

import com.young.book.springboot.config.auth.LoginUser;
import com.young.book.springboot.config.auth.dto.SessionUser;
import com.young.book.springboot.service.posts.PostsService;
import com.young.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    // private final HttpSession httpSession;

    /*
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        //SessionUser user = (SessionUser) httpSession.getAttribute("user"); 나쁜코드 -> 메소드 인자로 세션값을 바로 받을수 있기 위해 LoginUser 만듬.
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index"; // src/main/resources/templates/ + .mustache 로 전환되어 ViewResolver가 처리
    }
    */

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) { // 메소드 인자로 세션값을 바로 받을수있음. 어디서든 @LoginUser SessionUser user 지정해주면 세션 정보를 가져올 수 있게됨.
        model.addAttribute("posts", postsService.findAllDesc());
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }


    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

}
