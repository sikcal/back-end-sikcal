package com.prob_jr.sikcal_app.domain.service;

import com.prob_jr.sikcal_app.domain.Food;
import com.prob_jr.sikcal_app.domain.Member;
import com.prob_jr.sikcal_app.domain.Record;
import com.prob_jr.sikcal_app.domain.RecordFood;
import com.prob_jr.sikcal_app.domain.controller.dto.CreateRecordInfo;
import com.prob_jr.sikcal_app.domain.controller.dto.SaveFoodInfo;
import com.prob_jr.sikcal_app.domain.repository.*;
import com.prob_jr.sikcal_app.domain.service.dto.FoodDeleteDto;
import com.prob_jr.sikcal_app.domain.service.dto.FoodSaveDto;
import com.prob_jr.sikcal_app.domain.service.dto.FoodShowCond;
import com.prob_jr.sikcal_app.domain.service.dto.RecordFoodSearchCond;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.prob_jr.sikcal_app.domain.RecordFood.minusNutrition;

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
    public CreateRecordInfo record(String memberId) {
        //엔티티 조회
        Member member = memberRepository.findById(memberId).orElseThrow(null);
        //식단 생성
        Record record = Record.createRecord(member);
        recordRepository.save(record);

        return new CreateRecordInfo(record.getId(), record.getRecordDate());
    }


    /**
     * 식단 삭제
     */
    @Transactional
    public void deleteRecord(Long recordId) {
        //엔티티 조회
        List<RecordFood> foods = recordFoodRepository.findFoods(recordId);
        Record record = recordRepository.findById(recordId).orElseThrow(null);

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
    public SaveFoodInfo addFood(FoodSaveDto foodSaveDto) {
        Long recordId = foodSaveDto.getRecordId();
        Long foodId = foodSaveDto.getFoodId();

        // record 조회
        Record record = recordRepository.findById(recordId).orElseThrow(null);
        // food 조회
        Food food = foodRepository.findById(foodId).orElseThrow(null);
        // record food 생성
        RecordFood recordFood = RecordFood.createRecordFood(record, food);

        recordFoodRepository.save(recordFood);

        return new SaveFoodInfo(recordFood.getId());
    }

    /**
     * 음식 삭제
     */
    @Transactional
    public void deleteFood(FoodDeleteDto foodDeleteDto) {
        Long recordId = foodDeleteDto.getRecordId();
        Long recordFoodId = foodDeleteDto.getRecordFoodId();

        //Record 조회
        Record record = recordRepository.findById(recordId).orElseThrow(null);
        //RecordFood 조회
        RecordFood recordFood = recordFoodRepository.findOne(recordFoodId);

        minusNutrition(record, recordFood);

        //RecordFood 삭제
        recordFoodRepository.delete(recordFood);

    }

    /**
     * 음식 검색
     */
    public List<Food> search(RecordFoodSearchCond recordFoodSearchCond) {

        return foodRepository.findAllByFoodNameContains(recordFoodSearchCond.getFoodName());
    }

    /**
     * 식단을 통해 식단에 포함된 음식 정보를 반환
     */
    public List<Food> showFoods(FoodShowCond foodShowCond) {

        System.out.println("recordId = " + foodShowCond.getRecordId());

        return recordFoodRepository.findFoods(foodShowCond.getRecordId())
                .stream().map(RecordFood::getFood)
                .collect(Collectors.toList());
    }


}
