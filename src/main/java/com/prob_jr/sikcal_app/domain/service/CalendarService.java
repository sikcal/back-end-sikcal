package com.prob_jr.sikcal_app.domain.service;

import com.prob_jr.sikcal_app.domain.Member;
import com.prob_jr.sikcal_app.domain.repository.CalendarRepository;
import com.prob_jr.sikcal_app.domain.repository.MemberRepository;
import com.prob_jr.sikcal_app.domain.repository.RecordRepository;
import com.prob_jr.sikcal_app.domain.service.dto.WeightChangeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CalendarService {

    private final MemberRepository memberRepository;
    private final RecordRepository recordRepository;
    private final CalendarRepository calendarRepository;

    /**
     * 체중 변경
     */
    @Transactional
    public void changeWeight(WeightChangeDto weightChangeDto) {
        String memberId = weightChangeDto.getMemberId();
        int weight = weightChangeDto.getWeight();

        //멤버 조회
        Member member = memberRepository.findById(memberId).orElseThrow(null);
        //멤버의 현재 체중 변경
        member.setWeight(weight);

        memberRepository.save(member);
    }

    /**
     * 날짜별 목표 칼로리 달성 표시
     */
    @Transactional
    public void checkTargetKcal(String memberId, Long recordId) {
        //멤버 조회
        Member member = memberRepository.findById(memberId).orElseThrow();
        //해당 날짜에 맞는 식단 조회
//        recordRepository.findAll().stream().map()
        //인자 dto로 받기!!

    }













}
