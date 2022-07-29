package com.prob_jr.sikcal_app.domain.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {

    String memberId;
    Long postId;
    Long recordId;


    int totalCarbohydrate;
    int totalProtein;
    int totalFat;
    int totalKcal;
    String requiredFood;
    String menu;
    String recipe;
    String picUri;
}
