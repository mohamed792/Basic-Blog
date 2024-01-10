package com.example.basicblog.services.servicesimp;

import com.example.basicblog.domain.Post;
import com.example.basicblog.dtos.PostDto;
import com.example.basicblog.exceptions.ResourceNotFoundException;
import com.example.basicblog.mappers.PostMapper;
import com.example.basicblog.repositories.PostRepository;
import com.example.basicblog.services.PostService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.transaction.Transactional;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImp implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final EntityManagerFactory emf;

    @Autowired
    public PostServiceImp(PostRepository postRepository, PostMapper postMapper, EntityManagerFactory emf) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.emf = emf;
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

    @Transactional
    @Override
    public boolean deleteById(long id) {
        Post post = postRepository.findById(id).orElse(null);
        if (post == null)
            throw new ResourceNotFoundException(Post.class.getSimpleName(), "id", String.valueOf(id));
        postRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteByDto(PostDto postDto) {


        return false;
    }

    @Override
    @Transactional
    public PostDto update(PostDto postDto) {
// we used EM to get last updated version of post to returned
        EntityManager em = emf.createEntityManager();
        Post dbPost = em.find(Post.class, postDto.getId());
        Post toSave = postMapper.postDtoToPost(postDto);
        Post saved = em.merge(toSave);
        em.refresh(saved);
        System.out.println(saved);
        em.close();
        return postMapper.postToPostDto(saved);
        // implement using jpa repo
//        getById(postDto.getId());
//        Post dbpost = postRepository.saveAndFlush(postMapper.postDtoToPost(postDto));
//
//        System.out.println( getById(postDto.getId()));
//        return postMapper.postToPostDto(dbpost);
    }

    @Override
    public PostDto getById(long id) {
        Post post = postRepository.findById(id).orElse(null);
        if (post == null)
            throw new ResourceNotFoundException(Post.class.getSimpleName(), "id", String.valueOf(id));
        return postMapper.postToPostDto(post);
    }

    @Override
    public List<PostDto> getAll() {
        List<PostDto> dbPostDto = postRepository.findAll().stream().map(postMapper::postToPostDto).toList();
        return dbPostDto;
    }

    @Override
    public Page<PostDto> getByPage(Pageable page) {

        Page<PostDto> posts = postRepository.findAll(page).map(postMapper::postToPostDto);

        return posts;
    }


}
