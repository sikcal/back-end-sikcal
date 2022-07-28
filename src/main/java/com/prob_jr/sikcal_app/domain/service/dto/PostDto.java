package com.prob_jr.sikcal_app.domain.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    Long recordId;
    String menu;
    String recipe;
    String picUri;
}
