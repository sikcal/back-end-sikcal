package com.prob_jr.sikcal_app.domain.controller;

import com.prob_jr.sikcal_app.domain.controller.dto.ChangeWeightRequest;
import com.prob_jr.sikcal_app.domain.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class CalendarController {

    private final CalendarService calendarService;
    
    @PostMapping("/my-page/my-weight")
    public ResponseEntity<Void> changeWeight(@RequestBody ChangeWeightRequest changeWeightRequest) {
        calendarService.changeWeight(changeWeightRequest.toServiceDto());

        return ResponseEntity.ok().build();
    }

}
