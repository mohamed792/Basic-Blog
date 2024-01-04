package com.example.basicblog.controllers;

import com.example.basicblog.domain.Post;
import com.example.basicblog.dtos.PostDto;
import com.example.basicblog.exceptions.WrongMethodArgsExceptions;
import com.example.basicblog.services.PostService;
import jakarta.validation.Valid;
import org.aspectj.util.LangUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(value = "id") Optional<Long> id) {

        long pathId = id.orElseThrow(() -> new WrongMethodArgsExceptions(Post.class.getName(), "id", id.get().toString()));
        PostDto postDto = postService.getById(pathId);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }


    @PostMapping("")
    public ResponseEntity<PostDto> addPost(@Valid @RequestBody PostDto postDto) {

        PostDto savedPost = postService.add(postDto);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/api/v1/posts/" + savedPost.getId());
        return new ResponseEntity<>(savedPost, httpHeaders, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") long id) {

        boolean deleted = postService.deleteById(id);
        return new ResponseEntity<>("Deleted",HttpStatus.NO_CONTENT);
    }



    @PutMapping("/")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto){
        PostDto dbPost = postService.update(postDto);
        return  new ResponseEntity<>(dbPost,HttpStatus.ACCEPTED);

    }



}
