package com.prob_jr.sikcal_app.domain.controller;


import com.prob_jr.sikcal_app.domain.Entity.Role;
import com.prob_jr.sikcal_app.domain.exception.Constants;
import com.prob_jr.sikcal_app.domain.exception.SickalException;
import com.prob_jr.sikcal_app.domain.service.MemberService;
import com.prob_jr.sikcal_app.domain.service.dto.LoginDto;
import com.prob_jr.sikcal_app.domain.service.dto.MemberDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

//로그인 세션 유지하기
//Model객체에 memberDto라는 이름으로 저장된 데이터를 자동으로 세션에 저장
//로그인 폼에서 입력받은 커맨드 객체를 서비스 객체(memberService)를 통해 확인하여 유효한다면
//model객체에 조회한 데이터를 담고 유효하지 않다면다시 로그인페이지로 리다이렉트
// login 함수
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {
    /// Controller 표시로 스프링에서 객체를 하나 생성해서 들고 있음
    /// -> 스프링커네이너에서 빈이 관리됌
    private final MemberService memberService;
    private final Logger LOGGER = LoggerFactory.getLogger(MemberController.class);



    @PostMapping("/user/login")
    public ResponseEntity<MemberDto> login(@Valid @ModelAttribute("memberDto") LoginDto loginDto, BindingResult bindingResult,Model model) throws SickalException{
        if(bindingResult.hasErrors()){ // binding result valid의 형식에 맞지않으면 그에 맞ㄴ느 오류 생성됌
            //밑에 binding.reject를 사용해서 글로벌 오류 objectError생성
            throw new SickalException(Constants.ExceptionClass.Login,HttpStatus.BAD_REQUEST, "문제발생11");
        }
        MemberDto findMember = memberService.login(loginDto.getId(),loginDto.getPw());
        LOGGER.info("login정보: {}",findMember);
        if(findMember==null){
            //bindingResult.reject("loginFail","아이디 또는 비밀번호가 맞지 않습니다.");
            throw new SickalException(Constants.ExceptionClass.Login,HttpStatus.BAD_REQUEST, "아이디 또는 비밀번호가 맞지 않습니다.");
        }
        //세션에 저장
        //return ResponseEntity.ok().body(findMember);
        return ResponseEntity.ok().build();
        //throw new SickalException(Constants.ExceptionClass.Login,HttpStatus.OK,"로그인 성공");
    }

    //같은 url이더라도 데이터 조회는 get, 등록은 Post
    //객체로 받기
    /* 회원가입 */
    //오류 형식 프론트에게 어떤식으로 넘길것인가?
    //객체형식으로 잘못된값을 그대로 넘길것인가?
    //아님 에러 메시지만 넘길것인가
    //bindingresult를 써서 valid에 실패한 부분((MemberDto형식에 맞지않은)의 메세지를 보냄
    @PostMapping("/user/save")
    public  ResponseEntity<MemberDto>  joinProc(@Valid @RequestBody MemberDto memberDto, BindingResult bindingResult) throws SickalException {
        if (bindingResult.hasErrors()) {
            LOGGER.warn("형식에 맞지않은 입력입니다.:{}",bindingResult);
            /* 회원가입 실패시 입력 데이터 값을 유지 */
            /* 유효성 통과 못한 필드와 메시지를 핸들링
            Map<String, String> validatorResult = memberService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }*/
            //error throw
            //throw  new SickalException(Constants.ExceptionClass.MEMBER,HttpStatus.BAD_REQUEST, "");
            throw new SickalException(Constants.ExceptionClass.MEMBER,HttpStatus.BAD_REQUEST,"회원가입 형식이 잘못됌");
        }
        if (memberService.checkUsernameDuplication(memberDto.getId())){
            throw new SickalException(Constants.ExceptionClass.MEMBER,HttpStatus.NOT_ACCEPTABLE, "중복된 id가 있습니다.");
        }
        try {
           MemberDto memberDto1= memberService.join(memberDto);
           URI uri =URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());//head에 redirect할 uri주소를 프론트에게 보냄
             return ResponseEntity.created(uri).body(memberDto1); //code 201
        }
        catch (Exception e){
            throw new SickalException(Constants.ExceptionClass.MEMBER,HttpStatus.BAD_REQUEST, "뭔가 잘못된게 있음");
        }
        //throw new SickalException(Constants.ExceptionClass.MEMBER,HttpStatus.OK, "회원가입 성공 두 단계 다 통과함 ㅋ");
    }
    // 로그아웃시 session에서 제거해줘야함
    @GetMapping("/user/logout")
    public void logout(SessionStatus status) throws SickalException{
        //로그인 되있을때만 정상 로그아웃됌
        if(!status.isComplete()){
            status.setComplete();
        }
        else{
            throw new SickalException(Constants.ExceptionClass.Login,HttpStatus.BAD_REQUEST,"로그인부터 하고 로그아웃 누르셈 ㅋ");
        }
    }
    /**
     * 권한 저장 회원id + 권한이름 (user or admin)
     */
    @PostMapping("/role/save")
    public ResponseEntity<Role>saveRole(@RequestBody Role role){
        URI uri =URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());//head에 redirect할 uri주소를 프론트에게 보냄
        return ResponseEntity.created(uri).body(memberService.saveRole(role)); //코드 200 반환
    }
    // role을 member에게 부여
    @PostMapping("/role/addtouser")
    public ResponseEntity<?>addRoleToMember(@RequestBody RoleToMemberForm form){
        memberService.addToRoleToUser(form.getMemberId(),form.getRoleName());
        return ResponseEntity.ok().build(); //코드 200 반환
    }

    // custom exception만든거 test해보깅
    @PostMapping("user/exception")
    public void exceptionTest() throws SickalException{
        throw new SickalException(Constants.ExceptionClass.MEMBER,HttpStatus.BAD_REQUEST, "승우가 만든 ERROr");
    }

}
//Dto
@Data
class RoleToMemberForm{

    private String memberId;
    private String roleName;

}

