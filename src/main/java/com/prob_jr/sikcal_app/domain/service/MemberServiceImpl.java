package com.prob_jr.sikcal_app.domain.service;


import com.prob_jr.sikcal_app.domain.Entity.Member;
import com.prob_jr.sikcal_app.domain.Entity.MemberActivity;
import com.prob_jr.sikcal_app.domain.Entity.MemberSex;
import com.prob_jr.sikcal_app.domain.Entity.Role;
import com.prob_jr.sikcal_app.domain.controller.MemberController;
import com.prob_jr.sikcal_app.domain.repository.JpaMemberRepository;
import com.prob_jr.sikcal_app.domain.repository.RoleRepository;
import com.prob_jr.sikcal_app.domain.service.dto.InfoDto;
import com.prob_jr.sikcal_app.domain.service.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

//Service로 스프링 빈에 등록하기 그래야 autowired됌
//@Transactional 항상 롤백 시키는
@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService, UserDetailsService {
    private  final JpaMemberRepository memberRepository;
    private  final RoleRepository roleRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(MemberController.class);
    private final PasswordEncoder passwordEncoder;

    //spring이 db또는 사용자가 있는 곳 어디에서나 사용자를 불러오는데 사용하는 방법!
    @Override
    public UserDetails loadUserByUsername(String memberid) throws UsernameNotFoundException {
        Member member=memberRepository.findById(memberid).get();
        if(member==null){
            LOGGER.error("해당 id의 회원은 DB에 존재하지 않음");
            throw new UsernameNotFoundException("해당 id의 회원은 DB에 존재하지 않음");
        }else{
            LOGGER.info("멤버 디비에 존재함: {} ",member);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        member.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        //spring secirity 사용자를 return
        return new org.springframework.security.core.userdetails.User(member.getId(),member.getPassword(),authorities);
    }
    /**
     * 회원가입
     */
    @Override
    public MemberDto join(MemberDto memberDto){
        String encodedPassword = passwordEncoder.encode(memberDto.getPassword());
        memberDto.setPassword(encodedPassword);
        LOGGER.info("회원가입후 회원:{} DB에 입력 완료", memberDto.getName());
        return memberRepository.save(memberDto.toEntity()).toDto();
    }

    @Override
    public Role saveRole(Role role) {
        LOGGER.info("권한설정:{} DB에 입력 완료",role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addToRoleToUser(String userid, String roleName) {
        LOGGER.info("회원:{} 에게 권한:{} 부여 완료.",userid,roleName);
        Member member =memberRepository.findById(userid).get();
        Role role = roleRepository.findByName(roleName);
        member.getRoles().add(role);
    }

    @Override
    public MemberDto getMember(String id) {
        return memberRepository.findById(id).get().toDto();
    }

    //정규화 패턴 맞는지 확인하기
    //이정도는 서비스단에서 해결 디비 뒤질 필요없잖아
    @Transactional(readOnly = true)
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();
        /* 유효성 검사에 실패한 필드 목록을 받음 */
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());        }
        return validatorResult;
    }
    /* 아이디 중복 여부 확인 */
    @Transactional(readOnly = true)
    public boolean checkUsernameDuplication(String id) {
        return memberRepository.existsById(id);
    }
    //멤버 정보 갖고와서 기초대사량 등등 계산허기
   @Override
    public InfoDto searchInfoById(String id) {
        Optional<Member> member =memberRepository.findById(id);
        Member member1 = member.get();
        int age = calculateAge(member1.getBirth());
        double BMR= CalculateBMR(member1.getWeight(),member1.getHeight(),age,member1.getSex());
        double rate =CalculateActivity(member1.getActivity(),BMR);
        int suggestion=0;
        double[]ratio= new double[3];
        switch (member1.getGoal()){
            case GAIN:{
                suggestion=(int)rate+500;
                ratio[0]=0.5;ratio[1]=0.2;ratio[2]=0.3;break;
            }
            case LOSE:{
                suggestion=(int)rate-500;
                ratio[0]=0.3;ratio[1]=0.5;ratio[2]=0.2; break;
            }
            case REMAIN:{
                suggestion=(int)rate;
                ratio[0]=0.4;ratio[1]=0.4;ratio[2]=0.2;break;
            }
        }
       InfoDto infoDto = new InfoDto((int)(suggestion*ratio[0]),(int)(suggestion*ratio[1]), (int)(suggestion*ratio[2]),(suggestion));

       return infoDto;
    }

    @Override
    public MemberDto login(String id, String rawPw) {
        //인코딩해서 값 비교
        String encodedPassword = passwordEncoder.encode(rawPw);
        Member member =memberRepository.findById(id)
                .filter(m -> m.getPassword().equals(encodedPassword))
                .orElse(null);
        member.setPassword("security");
        MemberDto memberDto = member.toDto();
        return memberDto;
    }

    public double CalculateBMR(int weight, int height, int age, MemberSex sex){
        double BMR=((10*weight)+(6.25*height)-(5*age));
        switch (sex){
            case MAN:
            {
                BMR=BMR+5; break;
            }
            case WOMAN:
            {
                BMR=BMR-161; break;
            }
        }
        return BMR;
    }
    public double CalculateActivity(MemberActivity targetActivity, double BMR){
        double rate=0.0;
        switch (targetActivity){
            case LIGHT:{
                rate=BMR*1.375; break;
            }
            case NORMAL:{
                rate=BMR*1.55; break;
            }
            case HARD:{
                rate=BMR*1.725; break;
            }
            case SUPER:{
                rate=BMR*1.9; break;
            }
        }
        return rate;
    }

    public int calculateAge(LocalDate birth ){
        String birth1 = birth.toString();
        LocalDate now = LocalDate.now();
        LocalDate parsedBirthDate = LocalDate.parse(birth1, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        int age = now.minusYears(parsedBirthDate.getYear()).getYear(); // (1)

        // (2)
        // 생일이 지났는지 여부를 판단하기 위해 (1)을 입력받은 생년월일의 연도에 더한다.
        // 연도가 같아짐으로 생년월일만 판단할 수 있다!
        if (parsedBirthDate.plusYears(age).isAfter(now)) {
            age = age -1;
        }

        return age;
    }





/*
    private void validateDuplicateMember(Member member) {
        memberRepository.findById(member.getMember_id()).ifPresent(m-> {
            throw new IllegalStateException("이미 존재하는 회원입니다. ");
        });
    }*/

    /**
     * 전체 회원 조회

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }


*/
}
