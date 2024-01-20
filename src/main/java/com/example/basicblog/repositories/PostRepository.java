package com.example.basicblog.repositories;

import com.example.basicblog.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    @Query(value = "select p from Post p join fetch p.user")
    public List<Post>findAllWithUser();

    public Post findPostByTitleIgnoreCase(String title);
}
