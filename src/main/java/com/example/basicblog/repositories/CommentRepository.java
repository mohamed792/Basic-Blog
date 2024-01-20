package com.example.basicblog.repositories;

import com.example.basicblog.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    public List<Comment> findAllByPostId(long postId);
    @Query("select c from Comment c where c.post.id = :postId and c.id=:id")
    public Comment findCommentById(@Param("postId") long postId,@Param("id") long id);
}
