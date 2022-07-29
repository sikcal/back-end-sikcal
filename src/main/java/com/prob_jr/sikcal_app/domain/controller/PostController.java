package com.prob_jr.sikcal_app.domain.controller;

import com.prob_jr.sikcal_app.domain.Favorites;
import com.prob_jr.sikcal_app.domain.Post;
import com.prob_jr.sikcal_app.domain.config.TokenIdUtil;
import com.prob_jr.sikcal_app.domain.controller.dto.AddPost;
import com.prob_jr.sikcal_app.domain.controller.dto.addPostInfo;
import com.prob_jr.sikcal_app.domain.service.PostService;
import com.prob_jr.sikcal_app.domain.service.dto.PostDto;
import com.prob_jr.sikcal_app.domain.service.FavoritesService;
import com.prob_jr.sikcal_app.domain.service.dto.RecipeDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.http.HttpStatus;
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
    private final Logger LOGGER = LoggerFactory.getLogger(MemberController.class);


    /**
     *post작성해보기
     **/
    @PostMapping("/record/post")
    public ResponseEntity<addPostInfo> addPost(@RequestBody AddPost dto){
        addPostInfo postInfo =postService.addPost(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(postInfo);
    }
    /**
     *postList갖고오기 내림차순 정렬로
     */
    @GetMapping("/record/postlist")
    public ResponseEntity<List<PostDto>> getPosts(){
         List<PostDto> posts =postService.getPosts();
        return ResponseEntity.ok().body(posts);
    }
    /**
     * 좋아요 업뎃하고다시 반환
     */
    @PutMapping("/record/post/likes")
    public ResponseEntity<PostDto> clickLikes(@RequestParam("postId") Long postId ){
        postService.clickLikes(postId);
        PostDto post = postService.getPost(postId);
        return ResponseEntity.ok().body(post);
    }
    /**
     * postid로 하나의 post만 갖고오기
     */
    @GetMapping("/record/post")
    public ResponseEntity<PostDto> getPost(@RequestParam("postId") Long postId){
        PostDto post = postService.getPost(postId);
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
    public ResponseEntity<?> addFavorites(HttpServletRequest request, @RequestParam("postId") Long postId ){
        String member_id = TokenIdUtil.Decoder(request);
        favoritesService.addFavorites(member_id,postId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/record/post/search")
    public ResponseEntity<List<PostDto>> searchPosts(@RequestParam(value = "keyword",required = false) String keyword){ //302코드 found
        List<PostDto> results = postService.searchPosts(keyword.toString());
        LOGGER.info("입력받은 키워드 :{}",keyword);
        LOGGER.info("들어온 결과값 :{}",results);
        return ResponseEntity.status(HttpStatus.FOUND).body(results);
    }

}
