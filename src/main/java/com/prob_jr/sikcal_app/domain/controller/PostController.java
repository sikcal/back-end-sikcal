package com.prob_jr.sikcal_app.domain.controller;

import com.prob_jr.sikcal_app.domain.Favorites;
import com.prob_jr.sikcal_app.domain.Post;
import com.prob_jr.sikcal_app.domain.config.TokenIdUtil;
import com.prob_jr.sikcal_app.domain.controller.dto.addPostInfo;
import com.prob_jr.sikcal_app.domain.service.PostService;
import com.prob_jr.sikcal_app.domain.service.dto.PostDto;
import com.prob_jr.sikcal_app.domain.service.FavoritesService;
import com.prob_jr.sikcal_app.domain.service.dto.RecipeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class PostController {

    private final FavoritesService favoritesService;
    private final PostService postService;

    /**
     *post작성해보기
     **/
    @PostMapping("/record/post")
    public ResponseEntity<addPostInfo> addPost(@RequestBody PostDto dto){
        addPostInfo post =postService.addPost(dto);
        return ResponseEntity.ok().body(post);
    }
    /**
     *postList갖고오기 내림차순 정렬로
     */
    @GetMapping("/record/postlist")
    public ResponseEntity<List<Post>> getPosts(){
         List<Post> posts =postService.getPosts();
        return ResponseEntity.ok().body(posts);
    }
    /**
     *  수정 -----------아직 미구현
     */
    @DeleteMapping ("/record/post/update")
    public ResponseEntity<?> updatePost( ){

        return ResponseEntity.ok().build();
    }
    /**
     * 좋아요 업뎃하고다시 반환
     */
    @PutMapping("/record/post/likes")
    public ResponseEntity<Post> clickLikes(@RequestParam Long postId ){
        postService.clickLikes(postId);
        Post post = postService.getPost(postId);
        return ResponseEntity.ok().body(post);
    }
    /**
     * postid로 하나의 post만 갖고오기
     */
    @GetMapping("/record/post")
    public ResponseEntity<Post> getPost(@RequestParam Long postId){
        Post post = postService.getPost(postId);
        return ResponseEntity.ok().body(post);
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

    /**
     * post에서 즐겨찾기로
     */

    @PostMapping("/record/addfavorites")
    public ResponseEntity<Favorites> addFavorites(HttpServletRequest request, @RequestParam Long postId ){
        String member_id = TokenIdUtil.Decoder(request);
        return ResponseEntity.ok().body(favoritesService.addFavorites(member_id,postId));
    }

}
