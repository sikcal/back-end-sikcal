package com.prob_jr.sikcal_app.domain.service;


import com.prob_jr.sikcal_app.domain.Role;
import com.prob_jr.sikcal_app.domain.controller.dto.MemberWeightInfo;
import com.prob_jr.sikcal_app.domain.service.dto.InfoDto;
import com.prob_jr.sikcal_app.domain.service.dto.MemberDto;
import com.prob_jr.sikcal_app.domain.service.dto.WeightChangeDto;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.Map;

@Service
public interface MemberService {
    MemberDto join(MemberDto memberDto);
    Role saveRole(Role role);
    void addToRoleToUser(String userid,String roleName);
    MemberDto getMember(String userid);
    boolean checkUsernameDuplication(String id);
    InfoDto searchInfoById(String id);
    MemberWeightInfo changeWeight(WeightChangeDto weightChangeDto);

}
