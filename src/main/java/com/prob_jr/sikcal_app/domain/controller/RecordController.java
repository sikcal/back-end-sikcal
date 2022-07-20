package com.prob_jr.sikcal_app.domain.controller;

import com.prob_jr.sikcal_app.domain.Favorites;
import com.prob_jr.sikcal_app.domain.config.TokenIdUtil;
import com.prob_jr.sikcal_app.domain.controller.dto.CreateRecordInfo;
import com.prob_jr.sikcal_app.domain.controller.dto.CreateRecordRequest;
import com.prob_jr.sikcal_app.domain.service.FavoritesService;
import com.prob_jr.sikcal_app.domain.service.RecordFoodService;
import com.prob_jr.sikcal_app.domain.service.dto.RecipeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class RecordController {

    private final RecordFoodService recordFoodService;
    private final FavoritesService favoritesService;

    @PostMapping("/record")
    public ResponseEntity<CreateRecordInfo> record(@RequestBody CreateRecordRequest createRequest) {
        CreateRecordInfo createRecordInfo = recordFoodService.record(createRequest.toServiceDto());

        return ResponseEntity.status(HttpStatus.CREATED).body(createRecordInfo);
    }

    @DeleteMapping("/record/{recordId}")
    public ResponseEntity<Void> deleteRecord(@PathVariable(value = "recordId") Long recordId) {
        recordFoodService.deleteRecord(recordId);

        return ResponseEntity.ok().build();
    }




}
