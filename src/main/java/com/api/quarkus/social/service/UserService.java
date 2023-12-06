package com.api.quarkus.social.service;

import com.api.quarkus.social.entity.User;
import com.api.quarkus.social.mapper.ModelMapperConfig;
import com.api.quarkus.social.model.user.UserRequest;
import com.api.quarkus.social.model.user.UserResponse;
import com.api.quarkus.social.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapperConfig modelMapperConfig;


    public List<UserResponse> findAll() {
        return this.userRepository.findAll()
                .stream().map(user -> this.modelMapperConfig.modelMapper().map(user, UserResponse.class)).toList();
    }

    public UserResponse findById(Long id) {
        var user = this.userRepository.findByIdOptional(id)
                .orElseThrow(() -> new RuntimeException("Object Not Found! Id: " + id));
        return this.modelMapperConfig.modelMapper().map(user, UserResponse.class);
    }

    @Transactional
    public UserResponse create(UserRequest userRequest) {
        var user = this.modelMapperConfig.modelMapper().map(userRequest, User.class);
        this.userRepository.persist(user);
        this.userRepository.flush();
        return this.modelMapperConfig.modelMapper().map(this.userRepository.findById(user.getId()), UserResponse.class);
    }

    @Transactional
    public void update(Long id, UserRequest userRequest) {
        var userResponse = this.findById(id);
        var userData = this.modelMapperConfig.modelMapper().map(userRequest, UserResponse.class);
        this.updateData(userResponse, userData);
    }

    @Transactional
    public void delete(Long id) {
        var userResponse = this.findById(id);
        this.userRepository.deleteById(userResponse.getId());
    }

    private void updateData(UserResponse response, UserResponse request) {
        response.setName(request.getName());
        response.setAge(request.getAge());
    }
}
