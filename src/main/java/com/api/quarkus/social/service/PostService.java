package com.api.quarkus.social.service;

import com.api.quarkus.social.entity.Post;
import com.api.quarkus.social.entity.User;
import com.api.quarkus.social.mapper.ModelMapperConfig;
import com.api.quarkus.social.model.post.PostRequest;
import com.api.quarkus.social.model.post.PostResponse;
import com.api.quarkus.social.repository.PostRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;
    private final ModelMapperConfig modelMapperConfig;

    public List<PostResponse> findAllByUser(Long userId) {
        var userResponse = this.userService.findById(userId);
        var posts = this.postRepository.list("user", Sort.descending("dateTime"),
                this.modelMapperConfig.modelMapper().map(userResponse, User.class));
        return posts.stream().map(post ->
                this.modelMapperConfig.modelMapper().map(post, PostResponse.class)).toList();
    }

    public PostResponse findById(Long id) {
        var post = this.postRepository.findByIdOptional(id)
                .orElseThrow(() -> new RuntimeException("Object Not Found! Id: " + id));
        return this.modelMapperConfig.modelMapper().map(post, PostResponse.class);
    }

    @Transactional
    public PostResponse create(Long userId, PostRequest postRequest) {
        var userResponse = this.userService.findById(userId);
        var post = this.modelMapperConfig.modelMapper().map(postRequest, Post.class);
        post.setUser(this.modelMapperConfig.modelMapper().map(userResponse, User.class));
        this.postRepository.persist(post);
        this.postRepository.flush();
        return this.modelMapperConfig.modelMapper().map(this.postRepository.findById(post.getId()), PostResponse.class);
    }

    @Transactional
    public void delete(Long id) {
        var postResponse = this.findById(id);
        this.postRepository.deleteById(postResponse.getId());
    }
}