package com.prob_jr.sikcal_app.domain.controller;

import com.prob_jr.sikcal_app.domain.Food;
import com.prob_jr.sikcal_app.domain.controller.dto.*;
import com.prob_jr.sikcal_app.domain.service.RecordFoodService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class FoodController {

    private final RecordFoodService recordFoodService;

    @PostMapping("/record/food")
    public ResponseEntity<SaveFoodInfo> saveFood(@RequestBody SaveFoodRequest saveFoodRequest) {
        SaveFoodInfo saveFoodInfo = recordFoodService.addFood(saveFoodRequest.toServiceDto());

        return ResponseEntity.ok(saveFoodInfo);
    }

    @DeleteMapping("/record/food")
    public ResponseEntity<Void> deleteFood(@RequestBody DeleteFoodRequest deleteFoodRequest) {

        recordFoodService.deleteFood(deleteFoodRequest.toServiceDto());

        return ResponseEntity.ok().build();
    }

    @GetMapping("/record/food")
    public ResponseEntity<List<FoodInfoResponse>> search(@ModelAttribute RecordFoodSearchRequest searchRequest) {

        List<Food> search = recordFoodService.search(searchRequest.toServiceDto());

        return ResponseEntity.ok(search.stream().map(FoodInfoResponse::from).collect(Collectors.toList()));
    }

}
