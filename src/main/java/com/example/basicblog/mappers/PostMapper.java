package com.example.basicblog.mappers;

import com.example.basicblog.domain.Post;
import com.example.basicblog.dtos.PostDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {

    Post postDtoToPost(PostDto postDto);
    PostDto postToPostDto(Post post);
}
