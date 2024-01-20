package com.example.basicblog.mappers;

import com.example.basicblog.domain.Post;
import com.example.basicblog.dtos.PostDto;
import com.example.basicblog.dtos.PostWithUserIdDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "Spring")
public interface PostWithUserIdMapper {

    @Mapping(target = "userId", source = "post.user.id")
    PostWithUserIdDto postToPostDto(Post post);
}
