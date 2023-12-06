package com.api.quarkus.social.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class ModelMapperConfig {
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
