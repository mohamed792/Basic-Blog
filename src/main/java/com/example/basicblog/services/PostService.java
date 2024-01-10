package com.example.basicblog.services;

import com.example.basicblog.dtos.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {


    public PostDto add(PostDto postDto);
    public boolean deleteById(long id);
    public boolean deleteByDto(PostDto postDto);
    public  PostDto update(PostDto postDto);
    public PostDto getById(long id);
    public List<PostDto> getAll();

    public Page<PostDto> getByPage(Pageable page);



}
