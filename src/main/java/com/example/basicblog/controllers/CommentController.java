package com.example.basicblog.controllers;

import com.example.basicblog.dtos.CommentDto;
import com.example.basicblog.services.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CommentController {


    private final CommentService commentService;


    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{post_id}/comments")
    public ResponseEntity<CommentDto> addPostComment(@PathVariable(name = "post_id") long postId, @RequestBody CommentDto comment) {
        CommentDto commentDto = commentService.add(postId, comment);
        return new ResponseEntity<>(commentDto, HttpStatus.CREATED);
    }

    @GetMapping("/posts/{post_id}/comments")
    public ResponseEntity<List<CommentDto>> getCommentPerPost(@PathVariable(name = "post_id") long postId) {

        List<CommentDto> commentDtos = commentService.getCommentPerPost(postId);
        return new ResponseEntity<>(commentDtos, HttpStatus.OK);

    }

    @GetMapping("/posts/{post_id}/comments/{comment_id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(name = "post_id") long postId,
                                                     @PathVariable(name = "comment_id") long commentId) {
        return new ResponseEntity<>(commentService.getCommentById(postId, commentId), HttpStatus.OK);
    }

    @PutMapping("/posts/{post_id}/comments")
    public ResponseEntity<CommentDto> updateComment(@PathVariable("post_id") long postId,@Valid @RequestBody CommentDto comment){
        CommentDto commentDto = commentService.updateComment(postId,comment);
        return new ResponseEntity<>(commentDto,HttpStatus.CREATED);
    }

    @DeleteMapping("/posts/{post_id}/comments/{comment_id}")
    public ResponseEntity deleteComment(@PathVariable("post_id") long postId,@PathVariable("comment_id")long commentId){
        commentService.deleteComment(postId,commentId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
