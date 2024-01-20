package com.example.basicblog.services.servicesimp;

import com.example.basicblog.domain.Comment;
import com.example.basicblog.domain.Post;
import com.example.basicblog.dtos.CommentDto;
import com.example.basicblog.dtos.PostDto;
import com.example.basicblog.exceptions.ResourceNotFoundException;
import com.example.basicblog.mappers.CommentMapper;
import com.example.basicblog.repositories.CommentRepository;
import com.example.basicblog.repositories.PostRepository;
import com.example.basicblog.services.CommentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImp implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final CommentMapper commentMapper;

    @Autowired
    public CommentServiceImp(CommentRepository commentRepository, PostRepository postRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    @Transactional
    public CommentDto add(long postID, CommentDto commentDto) {

        Post post = postRepository.findById(postID).orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(postID)));
        commentDto.setPublishedAt(LocalDateTime.now());
        Comment comment = commentMapper.commentDtoToComment(commentDto);
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);
        return commentMapper.commentToCommentDto(savedComment);
    }

    @Override
    public List<CommentDto> getCommentPerPost(long postID) {

        Post post = postRepository.findById(postID).orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(postID)));
        List<CommentDto> comments = commentRepository.findAllByPostId(postID).stream().map(commentMapper::commentToCommentDto).toList();

        return comments;
    }

    @Override
    public CommentDto getCommentById(long postId, long commentId) {
        postExist(postId);
        return commentMapper.commentToCommentDto(commentRepository.findCommentById(postId, commentId));
    }

    @Override
    @Transactional
    public CommentDto updateComment(long postId, CommentDto commentDto) {

        Post post = postExist(postId);
        Comment comment = commentRepository.findById(commentDto.getId()).
                orElseThrow(() -> new ResourceNotFoundException("Comment", "id", String.valueOf(commentDto.getId())));
        comment.setContent(commentDto.getContent());
        comment.setTitle(commentDto.getTitle());
        comment.setPublishedAt(LocalDateTime.now());
        commentRepository.save(comment);   //optional as we are inside transaction
        return commentMapper.commentToCommentDto(comment);
    }

    @Override
    @Transactional
    public CommentDto deleteComment(long postId, long commentId) {

        Post post = postExist(postId);
        Comment comment = commentRepository.findById(commentId).
                orElseThrow(() -> new ResourceNotFoundException("Comment", "id", String.valueOf(commentId)));
        commentRepository.delete(comment);

        return commentMapper.commentToCommentDto(comment);
    }


    private Post postExist(long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(postId)));
        return post;
    }

}
