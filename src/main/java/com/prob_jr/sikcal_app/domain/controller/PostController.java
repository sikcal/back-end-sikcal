package com.prob_jr.sikcal_app.domain.controller;

import com.prob_jr.sikcal_app.domain.config.TokenIdUtil;
import com.prob_jr.sikcal_app.domain.service.FavoritesService;
import com.prob_jr.sikcal_app.domain.service.dto.RecipeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")


public class PostController {

    private final FavoritesService favoritesService;

    /**
     *post작성해보기
     **/
    @PostMapping("/record/post")
    public ResponseEntity<?> addPost(RequestBody RecordToPostDto ){

        return ResponseEntity.ok().build();
    }
    /**
     *postList갖고오기 내림차순 정렬로
     */
    @GetMapping("/record/postlist")
    public ResponseEntity<?> getPost(RequestBody RecordToPostDto ){

        return ResponseEntity.ok().build();
    }
    /**
     *  수정
     */
    @DeleteMapping ("/record/post/update")
    public ResponseEntity<?> updatePost(RequestBody RecordToPostDto ){

        return ResponseEntity.ok().build();
    }
    /**
     *postList갖고오기 내림차순 정렬로
     */
    @PostMapping ("/record/post/likes")
    public ResponseEntity<?> clickLikes(RequestBody RecordToPostDto ){

        return ResponseEntity.ok().build();
    }


    /**
     *
     즐겨찾기 항목 가져오기
     */
    @GetMapping("record/my-favorites")
    public ResponseEntity<List<RecipeDto>> getFavorites(HttpServletRequest request){
        String member_id = TokenIdUtil.Decoder(request);
        return ResponseEntity.ok().body(favoritesService.MyFavorites(member_id));
    }

}
