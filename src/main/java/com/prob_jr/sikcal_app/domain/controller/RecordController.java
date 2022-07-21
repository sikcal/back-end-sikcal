package com.prob_jr.sikcal_app.domain.controller;

import com.prob_jr.sikcal_app.domain.config.TokenIdUtil;
import com.prob_jr.sikcal_app.domain.controller.dto.CreateRecordInfo;
import com.prob_jr.sikcal_app.domain.service.RecordFoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class RecordController {

    private final RecordFoodService recordFoodService;

    @PostMapping("/record")
    public ResponseEntity<CreateRecordInfo> record(HttpServletRequest request) {
        String memberId = TokenIdUtil.Decoder(request);

        CreateRecordInfo createRecordInfo = recordFoodService.record(memberId);

        return ResponseEntity.status(HttpStatus.CREATED).body(createRecordInfo);
    }

    @DeleteMapping("/record/{recordId}")
    public ResponseEntity<Void> deleteRecord(@PathVariable(value = "recordId") Long recordId) {
        recordFoodService.deleteRecord(recordId);

        return ResponseEntity.ok().build();
    }

}
