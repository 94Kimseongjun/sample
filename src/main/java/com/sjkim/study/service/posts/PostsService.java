package com.sjkim.study.service.posts;

import com.sjkim.study.config.auth.LoginUser;
import com.sjkim.study.config.auth.dto.SessionUser;
import com.sjkim.study.domain.posts.Posts;
import com.sjkim.study.domain.posts.PostsRepository;
import com.sjkim.study.domain.user.UserRepository;
import com.sjkim.study.web.dto.PostsListResponseDto;
import com.sjkim.study.web.dto.PostsResponseDto;
import com.sjkim.study.web.dto.PostsSaveRequestDto;
import com.sjkim.study.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;
    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return  postsRepository.save(requestDto.toEntity()).getId();
    }


    @Transactional

    public Long update(Long id, PostsUpdateRequestDto requestDto, SessionUser user){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        if (!user.getName().equals(requestDto.getAuthor())){
            throw new IllegalArgumentException("권한이 업습니다.");
        }
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById (Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postsRepository.delete(posts);
    }
}
