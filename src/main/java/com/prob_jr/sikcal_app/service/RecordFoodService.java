package com.prob_jr.sikcal_app.domain.service;

<<<<<<< Updated upstream:src/main/java/com/prob_jr/sikcal_app/service/RecordFoodService.java
import com.prob_jr.sikcal_app.domain.Food;
import com.prob_jr.sikcal_app.domain.Member;
import com.prob_jr.sikcal_app.domain.Record;
import com.prob_jr.sikcal_app.domain.RecordFood;
import com.prob_jr.sikcal_app.domain.repository.FoodRepository;
import com.prob_jr.sikcal_app.domain.repository.MemberRepository;
import com.prob_jr.sikcal_app.domain.repository.RecordFoodRepository;
import com.prob_jr.sikcal_app.domain.repository.RecordRepository;
=======
import com.prob_jr.sikcal_app.domain.Entity.Food;
import com.prob_jr.sikcal_app.domain.Entity.Member;
import com.prob_jr.sikcal_app.domain.Entity.Record;
import com.prob_jr.sikcal_app.domain.Entity.RecordFood;
import com.prob_jr.sikcal_app.domain.repository.*;
import com.prob_jr.sikcal_app.domain.service.dto.FoodRecordDto;
import com.prob_jr.sikcal_app.domain.service.dto.FoodSaveDto;
import com.prob_jr.sikcal_app.domain.service.dto.RecordFoodSearchCond;
>>>>>>> Stashed changes:src/main/java/com/prob_jr/sikcal_app/domain/service/RecordFoodService.java
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecordFoodService {

    private final RecordRepository recordRepository;
    private final JpaMemberRepository memberRepository;
    private final FoodRepository foodRepository;
    private final RecordFoodRepository recordFoodRepository;

    /**
     * 식단 생성
     */
    /*
    @Transactional
    public Long record(Long memberId) {
        //엔티티 조회
       // Member member = memberRepository.findById(memberId);

        //식단 생성
     //   Record record = Record.createRecord(member);

        recordRepository.save(record);

        return record.getId();
    }*/


    /**
     * 식단 삭제
     */
    @Transactional
    public void cancelRecord(Long recordId) {
        //엔티티 조회
        List<RecordFood> foods = recordFoodRepository.findFoods(recordId);
        Record record = recordRepository.findOne(recordId);

        //하나씩 recordfood 전부 삭제
        for (RecordFood recordFood : foods) {
            foods.remove(recordFood);
        }
        //record 삭제
        recordRepository.cancel(record);
    }



    /**
     * 음식 추가
     */
    @Transactional
    public Long addFood(Long recordId, Long foodId) {
        // record 조회
        Record record = recordRepository.findOne(recordId);
        // food 조회
        Food food = foodRepository.findOne(foodId);
        // record food 생성
        RecordFood recordFood = RecordFood.createRecordFood(record, food);

        recordFoodRepository.save(recordFood);

        return recordFood.getId();
    }

    /**
     * 음식 삭제
     */


    /**
     * 음식 검색
     */

}
