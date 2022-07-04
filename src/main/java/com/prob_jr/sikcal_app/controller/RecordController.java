package com.prob_jr.sikcal_app.controller;

import com.prob_jr.sikcal_app.domain.RecordFood;
import com.prob_jr.sikcal_app.domain.repository.FoodSearch;
import com.prob_jr.sikcal_app.domain.service.RecordFoodService;
import com.prob_jr.sikcal_app.domain.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
public class RecordController {

    private final RecordFoodService recordFoodService;

    @PostMapping("/api/record/{id}")
    public ResponseEntity record(@PathVariable Long id) {
        Long recordId = recordFoodService.record(id);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
