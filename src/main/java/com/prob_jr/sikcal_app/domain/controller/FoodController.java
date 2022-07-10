package com.prob_jr.sikcal_app.domain.controller;

import com.prob_jr.sikcal_app.domain.Entity.Food;
import com.prob_jr.sikcal_app.domain.controller.dto.FoodInfoResponse;
import com.prob_jr.sikcal_app.domain.controller.dto.RecordFoodSearchRequest;
import com.prob_jr.sikcal_app.domain.controller.dto.SaveFoodRequest;
import com.prob_jr.sikcal_app.domain.service.RecordFoodService;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FoodController {
    private final Logger LOGGER = LoggerFactory.getLogger(MemberController.class);

    private final RecordFoodService recordFoodService;

    @PostMapping("/record/food")
    public ResponseEntity<Void> saveFood(@RequestBody SaveFoodRequest saveFoodRequest) {
        recordFoodService.addFood(saveFoodRequest.toServiceDto());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/record/food/{foodId}")
    public ResponseEntity<Void> deleteFood(@PathVariable(value = "foodId") Long foodId) {
        recordFoodService.deleteFood(foodId);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/record/food")
    public ResponseEntity<List<FoodInfoResponse>> search(@RequestBody RecordFoodSearchRequest searchRequest) {

        List<Food> search = recordFoodService.search(searchRequest.toServiceDto());
        LOGGER.info("제대로 입력? {} ", search);
        return ResponseEntity.ok(search.stream().map(FoodInfoResponse::from).collect(Collectors.toList()));
    }



}
