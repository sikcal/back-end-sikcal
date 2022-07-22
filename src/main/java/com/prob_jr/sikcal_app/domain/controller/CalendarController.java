package com.prob_jr.sikcal_app.domain.controller;

import com.prob_jr.sikcal_app.domain.Calendar;
import com.prob_jr.sikcal_app.domain.config.TokenIdUtil;
import com.prob_jr.sikcal_app.domain.controller.dto.CalendarStateInfo;
import com.prob_jr.sikcal_app.domain.controller.dto.CheckTargetRequest;
import com.prob_jr.sikcal_app.domain.controller.dto.MonthlyStateInfo;
import com.prob_jr.sikcal_app.domain.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class CalendarController {

    private final CalendarService calendarService;

//    @GetMapping("/calendar")
//    public ResponseEntity<CalendarStateInfo> checkTargetWeight(HttpServletRequest request) {
//        String memberId = TokenIdUtil.Decoder(request);
//
//        CalendarStateInfo calendarStateInfo = calendarService.checkTargetKcal(memberId);
//
//        return ResponseEntity.ok(calendarStateInfo);
//    }

    @GetMapping("/calendar")
    public ResponseEntity<List<MonthlyStateInfo>> checkMonthlyTarget(HttpServletRequest request,
                                                                     @ModelAttribute CheckTargetRequest checkTargetRequest) {
        String memberId = TokenIdUtil.Decoder(request);

        return ResponseEntity.ok(calendarService.checkMonthlyTarget(memberId, checkTargetRequest.toServiceDto())
                .stream().map(MonthlyStateInfo::from)
                .collect(Collectors.toList()));
    }
}
