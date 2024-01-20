package com.example.basicblog.services.servicesimp;

import com.example.basicblog.domain.Post;
import com.example.basicblog.dtos.PostDto;
import com.example.basicblog.dtos.PostWithUserIdDto;
import com.example.basicblog.exceptions.ResourceNotFoundException;
import com.example.basicblog.mappers.PostMapper;
import com.example.basicblog.mappers.PostWithUserIdMapper;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImp implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final PostWithUserIdMapper postWithUserIdMapper;
    private final EntityManagerFactory emf;

    @Autowired
    public PostServiceImp(PostRepository postRepository, PostMapper postMapper, EntityManagerFactory emf, PostWithUserIdMapper postWithUserIdMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.postWithUserIdMapper = postWithUserIdMapper;
        this.emf = emf;
    }


    @Transactional
    @Override
    public PostDto add(PostDto postDto) {
        postDto.setId(0);
        Post post = postMapper.postDtoToPost(postDto);
        Post temp = postRepository.findPostByTitleIgnoreCase(postDto.getTitle());
        if (postRepository.findPostByTitleIgnoreCase(postDto.getTitle()) != null) {
            throw new RuntimeException("Title '" + postDto.getTitle() + "' Already exist");
        }
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
    public PostDto update(PostDto postDto) {
//// we used EM to get last updated version of post to returned
        postExist(postDto.getId());
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Post dbPost = em.find(Post.class, postDto.getId());
        dbPost.setTitle(postDto.getTitle());
        dbPost.setContent(postDto.getContent());
        dbPost.setDescription(postDto.getDescription());
        dbPost.setLastUpdate(LocalDateTime.now());
        em.getTransaction().commit();
        em.close();
        return postMapper.postToPostDto(dbPost);

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
    public List<PostWithUserIdDto> getPostsWithUserID() {
        List<PostWithUserIdDto> posts = postRepository.findAllWithUser().stream().map(postWithUserIdMapper::postToPostDto).toList();
        return posts;
    }

    @Override
    public Page<PostDto> getByPage(Pageable page) {

        Page<PostDto> posts = postRepository.findAll(page).map(postMapper::postToPostDto);

        return posts;
    }
    private Post postExist(long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(postId)));
        return post;
    }

}
