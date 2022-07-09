package com.prob_jr.sikcal_app.Controller;

import com.prob_jr.sikcal_app.domain.Member;
import com.prob_jr.sikcal_app.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    /// Controller 표시로 스프링에서 객체를 하나 생성해서 들고 있음
    /// -> 스프링커네이너에서 빈이 관리됌
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) { //스프링빈에 등록되어있는 memberservice객체를 연결시켜줌 -> 의존성 주입
        this.memberService = memberService;
    }

    //같은 url이더라도 데이터 조회는 get, 등록은 Post
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create() {

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        //List<Member> members = memberService.join();
        //model.addAttribute("members", members);
        return "members/memberList";
    }
}