package com.prob_jr.sikcal_app.domain.controller.dto;

import lombok.Data;

@Data
public class AddPost {
    Long recordId;
    String menu;
    String recipe;
    String picUri;

}
