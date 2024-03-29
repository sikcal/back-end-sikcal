package com.prob_jr.sikcal_app.domain.repository;

import com.prob_jr.sikcal_app.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, String> {

    /**
     *  post한 게시글들 정렬후 가장 좋아요 많은 값 10개만 산출해서 내보냄
     */
    List<Post> findTop10ByOrderByNumOfLike();
    @Modifying
    @Query("update Post p set p.numOfLike=p.numOfLike+1 where p.id=:postId")
    void bulkLike(@Param("postId") Long postId);

    List<Post> searchPostByMenuContains(String keyword);

    Post findById(Long id);

}
