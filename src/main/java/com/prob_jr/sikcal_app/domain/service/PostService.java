package com.prob_jr.sikcal_app.domain.service;

import com.prob_jr.sikcal_app.domain.Post;
import com.prob_jr.sikcal_app.domain.Record;
import com.prob_jr.sikcal_app.domain.controller.MemberController;
import com.prob_jr.sikcal_app.domain.repository.RecordRepository;
import com.prob_jr.sikcal_app.domain.service.dto.PostDto;
import com.prob_jr.sikcal_app.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {


    private final Logger LOGGER = LoggerFactory.getLogger(MemberController.class);
    private final PostRepository postRepository;
    private final RecordRepository recordRepository;

    public Post addPost(PostDto dto){
        LOGGER.info("record에서 Post 저장 시작");
        Record record = recordRepository.getById(dto.getRecordID());
        Post post =postRepository.save(Post.createPost(record,dto.getMenu(), dto.getRecipe(), dto.getPicUri()));
        return post;
    }

    public List<Post> getPosts(){
        return postRepository.findTop10ByOrderByNumOfLike();
    }

    public void clickLikes(Long postId){
        postRepository.bulkLike(postId);
    }
    public Post getPost(Long postId){
        return postRepository.findById(postId);
    }


}
