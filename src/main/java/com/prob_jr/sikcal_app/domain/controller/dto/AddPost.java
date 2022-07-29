package com.prob_jr.sikcal_app.domain.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddPost {
    Long recordId;
    String menu;
    String recipe;
    String picUri;

}
