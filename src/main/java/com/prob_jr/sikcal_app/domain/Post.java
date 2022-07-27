package com.prob_jr.sikcal_app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.security.core.parameters.P;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter
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
    // 파이어베이스 사진 경로 담는 문자열
    private String picUri;
    @Lob
    private String requiredFood; //하나의 식단을 구성하는 음식을 concatenate하여 저장할 컬럼 || 토마토: ()g 계란 ()g 닭가슴살 ()g 청양고추 ()g

    @OneToOne(cascade = CascadeType.ALL ,orphanRemoval = true)
    @JoinColumn(name = "record_id")
    //@JsonIgnore
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
    public void setRequiredFood(String requiredFood) {
        this.requiredFood = requiredFood;
    }





    //====생성 메서드====//
    public static Post createPost(Record record, String menu, String recipe, String uri) {
        Post post = new Post();
        post.setMenu(menu);
        post.setRecord(record);
        post.setNumOfLike(0L);
        List<RecordFood> requiredFoods = record.getRecordFoods();
        StringBuilder sb = new StringBuilder();
        for(RecordFood recordFood :requiredFoods){
            sb.append(recordFood.getFood().getFoodName()+ ",");
        }
        post.setRequiredFood(sb.toString());
        post.setRecipe(recipe);
        post.setPicUri(uri);

        return post;
    }


}
