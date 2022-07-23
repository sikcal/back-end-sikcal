package com.prob_jr.sikcal_app.domain.service;

import com.prob_jr.sikcal_app.domain.Calendar;
import com.prob_jr.sikcal_app.domain.CalendarStatus;
import com.prob_jr.sikcal_app.domain.Member;
import com.prob_jr.sikcal_app.domain.Record;
import com.prob_jr.sikcal_app.domain.repository.CalendarRepository;
import com.prob_jr.sikcal_app.domain.repository.MemberRepository;
import com.prob_jr.sikcal_app.domain.repository.RecordRepository;
import com.prob_jr.sikcal_app.domain.service.dto.InfoDto;
import com.prob_jr.sikcal_app.domain.service.dto.MonthlyTargetDto;
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
     * 월 단위로 식단의 성공 실패 여부 저장 및 조회
     * 1. 다른 메소드를 만들어서 정각이 되면 알아서 success와 Fail을 저장하도록 만들기
     * 2. 또는 캘린더 화면으로 들어갈 때 해당 월 한달의 내역을 계산해서 보여주기
     * 1번이 더 효과적이긴 하지만 정각이 될 때 MemberId를 요청을 할 수가 없다
     * 따라서 2번으로 구현해야할 것 같음
     */
    @Transactional
    public List<Calendar> checkMonthlyTarget(String memberId, MonthlyTargetDto monthlyTargetDto) {
        LocalDate date = monthlyTargetDto.getDate();
        //멤버 조회
        Member member = memberRepository.findById(memberId).orElseThrow(null);
        //요청 받은 한달(한달이 지나지 않았으면 오늘 날짜까지) 식단 조회
        List<Record> monthlyRecord = recordRepository.findAllByMemberAndRecordDateBetween(member, date.withDayOfMonth(1), date);

        int[] totalKcalArr = new int[date.getDayOfMonth()];

        //배열의 인덱스+1을 날짜로 생각하여 해당 월에 날짜마다 총 먹은 칼로리를 저장
        for (Record record : monthlyRecord) {
            for (int i = 1; i <= date.getDayOfMonth(); i++) {
                if (record.getRecordDate().getDayOfMonth() == i) {
                    totalKcalArr[i - 1] += record.getTotalKcal();
                }
            }
        }

        //인자로 오는 멤버의 목표 섭취 칼로리를 가져옴
        InfoDto infoDto = memberService.searchInfoById(memberId);
        int requiredTotalKcal = infoDto.getRequiredTotal();

        //한달의 칼로리가 저장된 배열에서 성공 실패 여부를 확인
        for (int i = 0; i < totalKcalArr.length; i++) {

            if (calendarRepository.findAllByMemberAndCalendarDate(member, date.withDayOfMonth(i + 1)) == null) {
                Calendar calendar = Calendar.createCalendar(member, date.withDayOfMonth(i + 1));

                if (requiredTotalKcal <= totalKcalArr[i]) {
                    Calendar successCalendar = Calendar.updateStatus(calendar, CalendarStatus.SUCCESS);
                    calendarRepository.save(successCalendar);
                } else {
                    Calendar failCalendar = Calendar.updateStatus(calendar, CalendarStatus.FAIL);
                    calendarRepository.save(failCalendar);
                }
            }
            else {
                Calendar calendar = calendarRepository.findAllByMemberAndCalendarDate(member, date.withDayOfMonth(i + 1));
                if (requiredTotalKcal <= totalKcalArr[i]) {
                    Calendar successCalendar = Calendar.updateStatus(calendar, CalendarStatus.SUCCESS);
                    calendarRepository.save(successCalendar);
                } else {
                    Calendar failCalendar = Calendar.updateStatus(calendar, CalendarStatus.FAIL);
                    calendarRepository.save(failCalendar);
                }
            }

        }

        return calendarRepository.findAllByMemberAndCalendarDateBetween(member, date.withDayOfMonth(1), date);
    }

}
