package com.prob_jr.sikcal_app;

import com.prob_jr.sikcal_app.domain.MemberActivity;
import com.prob_jr.sikcal_app.domain.MemberGoal;
import com.prob_jr.sikcal_app.domain.MemberSex;
import com.prob_jr.sikcal_app.domain.Role;
import com.prob_jr.sikcal_app.domain.service.MemberService;
import com.prob_jr.sikcal_app.domain.service.dto.MemberDto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootApplication
public class SikcalAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SikcalAppApplication.class, args);
	}


	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}



	//테스트를 위한 초기회원들 입력
	@Bean
	CommandLineRunner run(MemberService memberService){
		return args -> {
			memberService.saveRole(new Role(null,"ROLE_USER"));
			memberService.saveRole(new Role(null,"ROLE_ADMIN"));

			memberService.join(new MemberDto(
					"kim12345","kim12345!!","승우김",171, LocalDate.of(1999, 5, 5), MemberSex.MAN, MemberActivity.HARD, MemberGoal.GAIN,71,new ArrayList<>()));
			//memberService.addToRoleToUser("kim12345","ROLE_USER");
			memberService.addToRoleToUser("kim12345","ROLE_ADMIN");

		};

	}
}
