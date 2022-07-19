package com.prob_jr.sikcal_app.domain.controller;

import com.prob_jr.sikcal_app.domain.CalendarStatus;
import com.prob_jr.sikcal_app.domain.controller.dto.CheckTargetRequest;
import com.prob_jr.sikcal_app.domain.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class CalendarController {

    private final CalendarService calendarService;

    @Scheduled(cron = "0 0 0 * * *")
    @GetMapping("/calendar-goal")
    public ResponseEntity<Void> checkTargetWeight(@RequestBody CheckTargetRequest checkTargetRequest) {
        calendarService.checkTargetKcal(checkTargetRequest.toServiceDto());

        return ResponseEntity.ok().build();
    }

}
