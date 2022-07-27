package com.prob_jr.sikcal_app.domain.controller.dto;

import lombok.Data;

@Data
public class addPostInfo {
    private Long postId;
    private Long recordId;

    public addPostInfo(Long postId, Long recordId) {
        this.postId = postId;
        this.recordId = recordId;
    }
}
