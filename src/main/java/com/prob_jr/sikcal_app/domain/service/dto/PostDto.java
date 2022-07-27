package com.prob_jr.sikcal_app.domain.service.dto;

import com.prob_jr.sikcal_app.domain.Member;
import com.prob_jr.sikcal_app.domain.Post;
import com.prob_jr.sikcal_app.domain.Record;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostDto {
    Long recordID;
    String menu;
    String recipe;
    String picUri;

    public PostDto(Long recordID, String menu, String recipe, String picUri) {
        this.recordID = recordID;
        this.menu = menu;
        this.recipe = recipe;
        this.picUri = picUri;
    }
}
