package com.example.basicblog.services;

import com.example.basicblog.dtos.PostDto;

import java.util.List;

public interface PostService {


    public PostDto add(PostDto postDto);
    public boolean deleteById(long id);
    public boolean deleteByDto(PostDto postDto);
    public  PostDto update(PostDto postDto);
    public PostDto getById(long id);
    public List<PostDto> getAll();



}
