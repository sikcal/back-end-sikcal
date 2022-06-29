package com.prob_jr.sikcal_app.domain.service;

import com.prob_jr.sikcal_app.domain.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FoodService {

    private final FoodRepository foodRepository;

    /**
     * 음식 추가
     */
//    @Transactional
//    public Long addFood() {
//
//    }



    /**
     * 음식 삭제
     */


    /**
     * 음식 검색
     */

}
