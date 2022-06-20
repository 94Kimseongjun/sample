package com.sjkim.study.web;

import com.sjkim.study.config.auth.LoginUser;
import com.sjkim.study.config.auth.dto.SessionUser;
import com.sjkim.study.service.posts.PostsService;
import com.sjkim.study.web.dto.PostsResponseDto;
import com.sjkim.study.web.dto.PostsSaveRequestDto;
import com.sjkim.study.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public  Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto, @LoginUser SessionUser user){
        return postsService.update(id, requestDto, user);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }

}
