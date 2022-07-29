package com.prob_jr.sikcal_app.domain.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prob_jr.sikcal_app.domain.Member;
import com.prob_jr.sikcal_app.domain.Post;
import com.prob_jr.sikcal_app.domain.Record;
import com.prob_jr.sikcal_app.domain.RecordFood;
import com.prob_jr.sikcal_app.domain.controller.MemberController;
import com.prob_jr.sikcal_app.domain.controller.dto.AddPost;
import com.prob_jr.sikcal_app.domain.controller.dto.addPostInfo;
import com.prob_jr.sikcal_app.domain.repository.RecordRepository;
import com.prob_jr.sikcal_app.domain.service.dto.PostDto;
import com.prob_jr.sikcal_app.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.startup.AddPortOffsetRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.SerializationUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {


    private final Logger LOGGER = LoggerFactory.getLogger(MemberController.class);
    private final PostRepository postRepository;
    private final RecordRepository recordRepository;

    public addPostInfo addPost(AddPost dto){
        LOGGER.info("record에서 Post 저장 시작");

        Record record = recordRepository.getById(dto.getRecordId());
        //record 백업용
        //Member member = record.getMember();
        Record recordBackUp = new Record(null,record.getMember()
                ,record.getRecordDate()
        ,record.getTotalCarbohydrate()
        ,record.getTotalProtein()
        ,record.getTotalFat()
        ,record.getTotalKcal()
        ,null);
        LOGGER.info("1단계 recordbackup: {}",recordBackUp.toString());
        Post setPost = Post.createPost(record,dto.getMenu(), dto.getRecipe(), dto.getPicUri());
        LOGGER.info("2단계 post: {}",setPost.toString());
        setPost.setRecord(recordBackUp);
        LOGGER.info("4단계 post: {}",setPost.toString());
        Record postRecord=recordRepository.save(recordBackUp);
        Post post =postRepository.save(setPost);
        addPostInfo postInfo =new addPostInfo(post.getId(), post.getRecord().getId());
        return postInfo;
    }

    public List<PostDto> getPosts(){
        return postRepository.findTop10ByOrderByNumOfLike()
                .stream()
                .map(post ->
                        post.toDto()
                ).collect(Collectors.toList());
    }
    public List<PostDto> searchPosts(String keyword){

        return postRepository.searchPostByMenuContains(keyword)
                .stream()
                .map(post-> post.toDto())
                .collect(Collectors.toList());
    }

    public void clickLikes(Long postId){
        postRepository.bulkLike(postId);
    }

    public PostDto getPost(Long postId){
        return postRepository.findById(postId).toDto();
    }




}
