package com.prob_jr.sikcal_app.domain.service;

import com.prob_jr.sikcal_app.domain.Food;
import com.prob_jr.sikcal_app.domain.Member;
import com.prob_jr.sikcal_app.domain.Record;
import com.prob_jr.sikcal_app.domain.RecordFood;
import com.prob_jr.sikcal_app.domain.repository.FoodRepository;
import com.prob_jr.sikcal_app.domain.repository.FoodSearch;
import com.prob_jr.sikcal_app.domain.repository.MemberRepository;
import com.prob_jr.sikcal_app.domain.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecordService {

    private final RecordRepository recordRepository;
    private final FoodRepository foodRepository;
    private final MemberRepository memberRepository;
//
//    /**
//     * 식단생성
//     */
//    public Long record(Long memberId, String foodName) {
//
//        //엔티티 조회
//        Member member = memberRepository.findOne(memberId);
//        Food food = foodRepository.findOne(foodName);
//
//        //음식 추가
//        RecordFood recordFood = RecordFood.createRecordFood(food);
//
//        //식단 추가
//        Record record = Record.createRecord(member, recordFood);
//
//        //식단 저장
//        recordRepository.save(record);
//
//        return record.getId();
//    }
//    // 식단 생성
//
//
//    /**
//     * 식단 삭제
//     */
//    public void cancelRecord(Long recordId) {
//        //엔티티 조회
//        Record record = recordRepository.findOne(recordId);
//        //식단 삭제
//        recordRepository.cancel(recordId);
//
//
//
//    }
//
//
//    /**
//     * 음식 검색
//     */
//    public List<RecordFood> findFoods(FoodSearch foodSearch) {
//
//        return recordRepository.findAll(foodSearch);
//    }

}
