package com.prob_jr.sikcal_app.domain.service;

import com.prob_jr.sikcal_app.domain.Food;
import com.prob_jr.sikcal_app.domain.Member;
import com.prob_jr.sikcal_app.domain.Record;
import com.prob_jr.sikcal_app.domain.RecordFood;
import com.prob_jr.sikcal_app.domain.repository.*;
import com.prob_jr.sikcal_app.domain.service.dto.FoodRecordDto;
import com.prob_jr.sikcal_app.domain.service.dto.FoodSaveDto;
import com.prob_jr.sikcal_app.domain.service.dto.RecordFoodSearchCond;
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
    public Long record(FoodRecordDto foodRecordDto) {
        String memberId = foodRecordDto.getMemberId();

        //엔티티 조회
        Member member = memberRepository.findById(memberId).orElseThrow(null);

        //식단 생성
        Record record = Record.createRecord(member);

        //식단의 탄단지 및 총 칼로리를 초기값인 0으로 설정
        record.setTotalCarbohydrate(0);
        record.setTotalProtein(0);
        record.setTotalFat(0);

        record.setTotalKcal(0);


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
    public Long addFood(FoodSaveDto foodSaveDto) {
        Long recordId = foodSaveDto.getRecordId();
        Long foodId = foodSaveDto.getFoodId();

        // record 조회
        Record record = recordRepository.findById(recordId).orElseThrow(null);
        // food 조회
        Food food = foodRepository.findById(foodId).orElseThrow(null);
        // record food 생성
        RecordFood recordFood = RecordFood.createRecordFood(record, food);

        //원래의 탄단지 및 총 칼로리에 추가된 음식의 탄단지 및 칼로리 양을 더해 추가된 값을 set
        record.setTotalCarbohydrate(record.getTotalCarbohydrate() + food.getCarbohydrate());
        record.setTotalProtein(record.getTotalProtein() + food.getProtein());
        record.setTotalFat(record.getTotalFat() + food.getFat());
        record.setTotalKcal(record.getTotalKcal() + food.getTotalKcal());

        recordFoodRepository.save(recordFood);

        return recordFood.getId();
    }

    /**
     * 음식 삭제
     */
    @Transactional
    public void deleteFood(Long recordId) {
        //Record 조회
        Record record = recordRepository.findById(recordId).orElseThrow(null);
        //RecordFood 조회
        RecordFood recordFood = recordFoodRepository.findOne(recordId);

        //식단에서 삭제한 음식의 탄단지 및 칼로리를 원래 식단의 값에서 빼줌
        record.setTotalCarbohydrate(record.getTotalCarbohydrate() - recordFood.getFood().getCarbohydrate());
        record.setTotalProtein(record.getTotalProtein() - recordFood.getFood().getProtein());
        record.setTotalFat(record.getTotalFat() - recordFood.getFood().getFat());
        record.setTotalKcal(record.getTotalKcal() - recordFood.getFood().getTotalKcal());

        //RecordFood 삭제
        recordFoodRepository.delete(recordFood);

    }

    /**
     * 음식 검색
     */
    public List<Food> search(RecordFoodSearchCond recordFoodSearchCond) {

        return foodRepository.findAllByFoodNameContains(recordFoodSearchCond.getFoodName());
    }



}
