package com.prob_jr.sikcal_app.domain.controller;

import com.prob_jr.sikcal_app.domain.controller.dto.CalendarStateInfo;
import com.prob_jr.sikcal_app.domain.controller.dto.CheckTargetRequest;
import com.prob_jr.sikcal_app.domain.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class CalendarController {

    private final CalendarService calendarService;

    @GetMapping("/calendar")
    public ResponseEntity<CalendarStateInfo> checkTargetWeight(@ModelAttribute CheckTargetRequest checkTargetRequest) {
        CalendarStateInfo calendarStateInfo = calendarService.checkTargetKcal(checkTargetRequest.toServiceDto());

        return ResponseEntity.ok(calendarStateInfo);
    }



}
