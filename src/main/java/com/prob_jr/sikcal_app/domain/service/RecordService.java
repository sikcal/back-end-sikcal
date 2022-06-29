package com.prob_jr.sikcal_app.domain.service;

import com.prob_jr.sikcal_app.domain.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecordService {

    private final RecordRepository recordRepository;

    /**
     * 식단추가
     */




    /**
     * 식단 삭제
     */

}
