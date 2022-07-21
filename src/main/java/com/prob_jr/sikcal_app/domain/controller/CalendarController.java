package com.prob_jr.sikcal_app.domain.controller;

import com.prob_jr.sikcal_app.domain.config.TokenIdUtil;
import com.prob_jr.sikcal_app.domain.controller.dto.CalendarStateInfo;
import com.prob_jr.sikcal_app.domain.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class CalendarController {

    private final CalendarService calendarService;

    @GetMapping("/calendar")
    public ResponseEntity<CalendarStateInfo> checkTargetWeight(HttpServletRequest request) {
        String memberId = TokenIdUtil.Decoder(request);

        CalendarStateInfo calendarStateInfo = calendarService.checkTargetKcal(memberId);

        return ResponseEntity.ok(calendarStateInfo);
    }



}
