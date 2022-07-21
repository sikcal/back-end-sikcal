package com.prob_jr.sikcal_app.domain;

import lombok.*;
import org.springframework.security.core.parameters.P;

import javax.persistence.*;

import java.time.LocalDate;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@Builder
@RequiredArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String menu;
    private Long numOfLike;
    @Lob
    private String recipe;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "record_id")
    private Record record;



    private void setRecord(Record record) {
        this.record = record;
    }

    private void setNumOfLike(Long numOfLike) {
        this.numOfLike = numOfLike;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public void setPicUri(String picUri) {
        this.picUri = picUri;
    }
    // 파이어베이스 사진 경로 담는 문자열
    private String picUri;

    //====생성 메서드====//
    public static Post createPost(Record record, String menu, String recipe, String uri) {
        Post post = new Post();
        post.setMenu(menu);
        post.setRecord(record);
        post.setNumOfLike(0L);
        post.setRecipe(recipe);
        post.setPicUri(uri);

        return post;
    }


}
