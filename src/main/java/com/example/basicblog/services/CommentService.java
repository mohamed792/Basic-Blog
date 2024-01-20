package com.example.basicblog.services;

import com.example.basicblog.dtos.CommentDto;

import java.util.List;

public interface CommentService {


    public CommentDto add(long postID ,CommentDto commentDto);
    public List<CommentDto> getCommentPerPost(long postID);
    public CommentDto getCommentById(long postId ,long commentId);

    public CommentDto updateComment(long postId ,CommentDto commentDto);
    public CommentDto deleteComment(long postId ,long commentId);

}
