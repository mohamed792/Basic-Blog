package com.example.basicblog.services.servicesimp;

import com.example.basicblog.domain.Post;
import com.example.basicblog.dtos.PostDto;
import com.example.basicblog.exceptions.ResourceNotFoundException;
import com.example.basicblog.mappers.PostMapper;
import com.example.basicblog.repositories.PostRepository;
import com.example.basicblog.services.PostService;
import jakarta.transaction.Transactional;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImp implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Autowired
    public PostServiceImp(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    @Transactional
    @Override
    public PostDto add(PostDto postDto) {

        Post post = postMapper.postDtoToPost(postDto);
        Post dbPost = postRepository.save(post);
        if (dbPost != null)
            return postMapper.postToPostDto(dbPost);
        return null;
    }

    @Override
    public boolean deleteById(long id) {
        Post post = postRepository.findById(id).orElse(null);
        if(post == null)
            throw new ResourceNotFoundException(Post.class.getSimpleName(),"id",String.valueOf(id));
        postRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteByDto(PostDto postDto) {


        return false;
    }

    @Override
    public PostDto update(PostDto postDto) {
        return null;
    }

    @Override
    public PostDto getById(long id) {
        Post post = postRepository.findById(id).orElse(null);
        if (post == null)
            throw new ResourceNotFoundException(Post.class.getSimpleName(),"id",String.valueOf(id));
        return postMapper.postToPostDto(post);
    }

    @Override
    public List<PostDto> getAll() {
        List<PostDto> dbPostDto = postRepository.findAll().stream().map(postMapper::postToPostDto).toList();
        return dbPostDto;
    }
}
