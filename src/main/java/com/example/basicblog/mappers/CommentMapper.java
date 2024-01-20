package com.example.basicblog.mappers;

import com.example.basicblog.domain.Comment;
import com.example.basicblog.dtos.CommentDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    public Comment commentDtoToComment(CommentDto dto);
    public CommentDto commentToCommentDto(Comment comment);

}
