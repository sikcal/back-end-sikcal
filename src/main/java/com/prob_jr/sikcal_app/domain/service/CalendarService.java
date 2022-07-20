package com.prob_jr.sikcal_app.domain.service;

import com.prob_jr.sikcal_app.domain.Calendar;
import com.prob_jr.sikcal_app.domain.CalendarStatus;
import com.prob_jr.sikcal_app.domain.Member;
import com.prob_jr.sikcal_app.domain.Record;
import com.prob_jr.sikcal_app.domain.controller.dto.CalendarStateInfo;
import com.prob_jr.sikcal_app.domain.repository.CalendarRepository;
import com.prob_jr.sikcal_app.domain.repository.MemberRepository;
import com.prob_jr.sikcal_app.domain.repository.RecordRepository;
import com.prob_jr.sikcal_app.domain.service.dto.InfoDto;
import com.prob_jr.sikcal_app.domain.service.dto.TargetKcalCheckDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CalendarService {

    private final MemberRepository memberRepository;
    private final RecordRepository recordRepository;
    private final CalendarRepository calendarRepository;
    private final MemberService memberService;

    /**
     * 날짜별 목표 칼로리 달성 표시
     */
    @Transactional
    public CalendarStateInfo checkTargetKcal(TargetKcalCheckDto targetKcalCheckDto) {
        String memberId = targetKcalCheckDto.getMemberId();
        LocalDate yesterday = LocalDate.now().minusDays(1);

        //멤버 조회
        Member member = memberRepository.findById(memberId).orElseThrow();
        //하루 전날의 식단 조회
        List<Record> recordList = recordRepository.findAllByRecordDateEquals(yesterday);
        //캘린더 생성
        Calendar calendar = Calendar.createCalendar(member);

        int exTotalKcal = 0;

        //인자로 오는 멤버의 식단의 총 칼로리만 새로운 변수에 더함
        for (Record record : recordList) {
            if (member.equals(record.getMember())) {
                exTotalKcal += record.getTotalKcal();
            }
        }
        //인자로 오는 멤버의 목표 섭취 칼로리를 가져옴
        InfoDto infoDto = memberService.searchInfoById(memberId);
        int requiredTotalKcal = infoDto.getRequiredTotal();

        //목표 섭취 칼로리와 전날의 총 섭취 칼로리를 비교
        if (requiredTotalKcal <= exTotalKcal) {
            Calendar successCalendar = Calendar.checkTarget(calendar, CalendarStatus.SUCCESS);
            calendarRepository.save(successCalendar);
            return new CalendarStateInfo(yesterday, CalendarStatus.SUCCESS);
        } else {
            Calendar failCalendar = Calendar.checkTarget(calendar, CalendarStatus.FAIL);
            calendarRepository.save(failCalendar);
            return new CalendarStateInfo(yesterday, CalendarStatus.FAIL);
        }
    }

}
