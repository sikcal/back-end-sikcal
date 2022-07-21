package com.prob_jr.sikcal_app.domain.service.dto;

import com.prob_jr.sikcal_app.domain.Member;
import com.prob_jr.sikcal_app.domain.Post;
import com.prob_jr.sikcal_app.domain.Record;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostDto {
    Long recordID;
    String menu;
    String recipe;
    String picUri;

}
