package com.prob_jr.sikcal_app.domain.service.dto;

import com.prob_jr.sikcal_app.domain.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;


//데이터 이동순서
// view(dto)-controller(dto)-service(dto->entity)-dao,db(entity)
//개발과정에서만 @Data씀 나중에 @Setter @Getter은 없앨거임
//@Getter은 dto말고 entity에서 가져옴
@Data
@NoArgsConstructor
@Builder //빌더없이는 맨날 주구장창 생성자에 맞게 ㅈㄴ넣어줘야함
// 빌더의 toentity로 손쉬운 값변경 가능 null이들어오더라도 가능 씹가능
public class MemberDto {

    @Size(min=8, max=12,message = "아이디는 8~12까지 만드셈")
    @Id
    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String id;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}",
            message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;


    @NotNull
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "이름은 특수문자를 제외한 2~10자리여야 합니다.")
    private String name;
    private int height;

    @DateTimeFormat(pattern= "yyyy-MM-dd")
    private LocalDate birth;

    private MemberSex sex;
    private MemberActivity activity;
    private MemberGoal goal;
    private int weight;
    private Collection<Role> roles= new ArrayList<>();



    public MemberDto(String id, String password, String name, int height, LocalDate birth, MemberSex sex, MemberActivity activity, MemberGoal goal, int weight,Collection<Role> roles) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.height = height;
        this.birth = birth;
        this.sex = sex;
        this.activity = activity;
        this.goal = goal;
        this.weight = weight;
        this.roles = roles;
    }

    public Member toEntity(){
        return Member.builder().id(id)
                .activity(activity)
                .birth(birth)
                .goal(goal)
                .height(height)
                .name(name)
                .password(password)
                .sex(sex)
                .weight(weight)
                .roles(roles)
                .build();
    }
}
