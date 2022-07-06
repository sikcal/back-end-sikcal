package com.prob_jr.sikcal_app.controller;

import com.prob_jr.sikcal_app.domain.service.RecordFoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RecordController {

    private final RecordFoodService recordFoodService;

    @PostMapping("/record/{id}")
    public ResponseEntity<Long> record(@PathVariable Long id) {
        Long recordId = recordFoodService.record(id);

        return new ResponseEntity<>(recordId, HttpStatus.OK);
    }

    @DeleteMapping("/record/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        recordFoodService.deleteRecord(id);

        return ResponseEntity.ok().build();
    }












}
