package com.example.basicblog.controllers;

import com.example.basicblog.domain.Post;
import com.example.basicblog.dtos.PostDto;
import com.example.basicblog.exceptions.WrongMethodArgsExceptions;
import com.example.basicblog.services.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.aspectj.util.LangUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
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
    public ResponseEntity<PostDto> addPost(@Valid @RequestBody PostDto postDto, HttpServletRequest request) {

        System.out.println(RequestContextUtils.getTimeZone(request));
        postDto.setCreationDate(LocalDateTime.now());
        PostDto savedPost = postService.add(postDto);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/api/v1/posts/" + savedPost.getId());
        return new ResponseEntity<>(savedPost, httpHeaders, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") long id) {

        boolean deleted = postService.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.NO_CONTENT);
    }


    @PutMapping("/")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto) {

        postDto.setLastUpdate(LocalDateTime.now());
        PostDto dbPost = postService.update(postDto);
        return new ResponseEntity<>(dbPost, HttpStatus.ACCEPTED);

    }

    @GetMapping(path = "/")
    public ResponseEntity<Page<PostDto>> getPostsByPage(
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "20", name = "size") int size,
            @RequestParam(defaultValue = "id", name = "sort") String sort) {

        boolean isSearchable = Arrays.stream(Post.class.getDeclaredFields()).map(Field::getName).toList().contains(sort);
        if(!isSearchable)
            throw  new RuntimeException("The search value ("+sort+") not allowed");

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<PostDto> posts = postService.getByPage(pageable);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }


}