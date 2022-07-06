package com.prob_jr.sikcal_app.controller;

import com.prob_jr.sikcal_app.domain.service.RecordFoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FoodController {

    private final RecordFoodService recordFoodService;

    @PostMapping("/search")
    public ResponseEntity<> addFood(@PathVariable ) {

    }
}
