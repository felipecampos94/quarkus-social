package com.api.quarkus.social.controller;

import com.api.quarkus.social.model.post.PostRequest;
import com.api.quarkus.social.service.PostService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

@Path("/users/{userId}/posts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GET
    public Response findAll(@PathParam("userId") Long userId) {
        return Response.ok(this.postService.findAllByUser(userId)).build();
    }

    @POST
    public Response create(@PathParam("userId") Long userId, PostRequest postRequest) {
        return Response.status(Response.Status.CREATED)
                .entity(this.postService.create(userId, postRequest)).build();
    }

    @DELETE
    public Response delete(@PathParam("userId") Long userId) {
        this.postService.delete(userId);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
