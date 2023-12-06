package com.api.quarkus.social.service;

import com.api.quarkus.social.entity.Follower;
import com.api.quarkus.social.entity.User;
import com.api.quarkus.social.mapper.ModelMapperConfig;
import com.api.quarkus.social.model.follower.FollowerRequest;
import com.api.quarkus.social.model.follower.FollowerResponse;
import com.api.quarkus.social.repository.FollowerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class FollowerService {
    private final FollowerRepository followerRepository;
    private final UserService userService;
    private final ModelMapperConfig modelMapperConfig;


    public List<FollowerResponse> findAll() {
        return this.followerRepository.findAll()
                .stream().map(follower -> this.modelMapperConfig.modelMapper().map(follower, FollowerResponse.class)).toList();
    }

    public FollowerResponse findById(Long id) {
        var follower = this.followerRepository.findByIdOptional(id)
                .orElseThrow(() -> new RuntimeException("Object Not Found! Id: " + id));
        return this.modelMapperConfig.modelMapper().map(follower, FollowerResponse.class);
    }

    @Transactional
    public FollowerResponse create(FollowerRequest followerRequest) {
        var follower = this.modelMapperConfig.modelMapper().map(followerRequest, Follower.class);
        this.followerRepository.persist(follower);
        this.followerRepository.flush();
        return this.modelMapperConfig.modelMapper().map(this.followerRepository.findById(follower.getId()), FollowerResponse.class);
    }

    @Transactional
    public FollowerResponse followUser(Long userId, FollowerRequest followerRequest) {

        var userResponse = this.userService.findById(userId);
        var followerResponse = this.findById(followerRequest.getFollowerId());
        var follows = this.followerRepository.follows(this.modelMapperConfig.modelMapper().map(followerResponse.getFollower(), User.class), this.modelMapperConfig.modelMapper().map(userResponse, User.class));
        if (!follows) {
            followerResponse.setUser(userResponse);
            followerResponse.setFollower(followerResponse.getFollower());

            this.followerRepository.persist(this.modelMapperConfig.modelMapper().map(followerResponse, Follower.class));
            this.followerRepository.flush();
        }
        return this.modelMapperConfig.modelMapper().map(this.followerRepository.findById(followerResponse.getId()), FollowerResponse.class);
    }

    @Transactional
    public void delete(Long id) {
        var followerResponse = this.findById(id);
        this.followerRepository.deleteById(followerResponse.getId());
    }
}
