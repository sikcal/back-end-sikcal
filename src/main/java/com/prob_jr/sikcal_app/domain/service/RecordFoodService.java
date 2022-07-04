package com.prob_jr.sikcal_app.domain.service;

import com.prob_jr.sikcal_app.domain.Food;
import com.prob_jr.sikcal_app.domain.Member;
import com.prob_jr.sikcal_app.domain.Record;
import com.prob_jr.sikcal_app.domain.RecordFood;
import com.prob_jr.sikcal_app.domain.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecordFoodService {

    private final RecordRepository recordRepository;
    private final MemberRepository memberRepository;
    private final FoodRepository foodRepository;
    private final RecordFoodRepository recordFoodRepository;

    /**
     * 식단 생성
     */
    @Transactional
    public Long record(Long memberId) {
        //엔티티 조회
        Member member = memberRepository.findOne(memberId);

        //식단 생성
        Record record = Record.createRecord(member);

        recordRepository.save(record);

        return record.getId();
    }


    /**
     * 식단 삭제
     */
    @Transactional
    public void deleteRecord(Long recordId) {
        //엔티티 조회
        List<RecordFood> foods = recordFoodRepository.findFoods(recordId);
        Record record = recordRepository.findOne(recordId);

        //하나씩 recordfood 전부 삭제
        for (RecordFood recordFood : foods) {
            recordFoodRepository.delete(recordFood);
        }
        //record 삭제
        recordRepository.delete(record);
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
    @Transactional
    public void deleteFood(Long recordId) {
        //Record 조회
        Record record = recordRepository.findOne(recordId);
        //RecordFood조회
        RecordFood recordFood = recordFoodRepository.findOne(recordId);
        //RecordFood삭제
        recordFoodRepository.delete(recordFood);

    }

    /**
     * 음식 검색
     */
//    public List<RecordFood> search(FoodSearch foodSearch) {
//
//
//    }




}
