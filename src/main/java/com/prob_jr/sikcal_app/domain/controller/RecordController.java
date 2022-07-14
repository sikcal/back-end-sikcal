package com.prob_jr.sikcal_app.domain.controller;

import com.prob_jr.sikcal_app.domain.controller.dto.CreateRecordRequest;
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

    @PostMapping("/record")
    public ResponseEntity<Void> record(@RequestBody CreateRecordRequest createRequest) {
        recordFoodService.record(createRequest.toServiceDto());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/record/{recordId}")
    public ResponseEntity<Void> deleteRecord(@PathVariable(value = "recordId") Long recordId) {
        recordFoodService.deleteRecord(recordId);

        return ResponseEntity.ok().build();
    }












}
