package com.sjkim.study.web;

import com.sjkim.study.config.auth.LoginUser;
import com.sjkim.study.config.auth.dto.SessionUser;
import com.sjkim.study.service.posts.PostsService;
import com.sjkim.study.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import javax.servlet.http.HttpSession;
@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts", postsService.findAllDesc());
        //SessionUser user = (SessionUser) httpSession.getAttribute("user");

        System.out.println(user);
        if (user != null){
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }


    /*
    @GetMapping("/posts/save")
    private String postsSave(){
        return "posts-save";
    }
     */
    @GetMapping("/posts/save")
    private String postsSave(Model model,@LoginUser SessionUser user){
        if (user != null){
            model.addAttribute("userName", user.getName());
        }
        return "posts-save";
    }


    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        PostsResponseDto dto = postsService.findById(id);
        if (user == null || !user.getName().equals(dto.getAuthor())){
            model.addAttribute("authority", "authority");
            return "/";
        }

        model.addAttribute("posts", dto);

        return "posts-update";

    }


    /*
    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model, @LoginUser SessionUser user) {

        PostsResponseDto dto = postsService.findById(id);
        if (!dto.getAuthor().equals(user.getName())){
            return "????????????";
        }
        model.addAttribute("posts", dto);

        return "posts-update";
    }
     */

}
