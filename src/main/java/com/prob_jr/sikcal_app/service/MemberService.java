package com.prob_jr.sikcal_app.service;

<<<<<<< Updated upstream:src/main/java/com/prob_jr/sikcal_app/service/MemberService.java
import com.prob_jr.sikcal_app.domain.Member;
import com.prob_jr.sikcal_app.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
=======
import com.prob_jr.sikcal_app.domain.Entity.Member;
import com.prob_jr.sikcal_app.domain.Entity.Role;
import com.prob_jr.sikcal_app.domain.service.dto.InfoDto;
import com.prob_jr.sikcal_app.domain.service.dto.MemberDto;
>>>>>>> Stashed changes:src/main/java/com/prob_jr/sikcal_app/domain/service/MemberService.java
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.Map;

@Service
public interface MemberService {
    MemberDto join(MemberDto memberDto);
    Role saveRole(Role role);
    void addToRoleToUser(String userid,String roleName);
    MemberDto getMember(String userid);
    Map<String, String> validateHandling(Errors errors);
    boolean checkUsernameDuplication(String id);
    InfoDto searchInfoById(String id);
    MemberDto login(String id, String pw);
}
