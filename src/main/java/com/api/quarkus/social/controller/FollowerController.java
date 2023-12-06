package com.api.quarkus.social.controller;

import com.api.quarkus.social.model.follower.FollowerRequest;
import com.api.quarkus.social.service.FollowerService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

@Path("/followers/{userId}/followers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class FollowerController {
    private final FollowerService followerService;

    @GET
    public Response findAll() {
        return Response.ok(this.followerService.findAll()).build();
    }

    @POST
    public Response create(FollowerRequest followerRequest) {
        return Response.status(Response.Status.CREATED)
                .entity(this.followerService.create(followerRequest)).build();
    }

    @PUT
    public Response followUser(@PathParam("userId") Long userId, FollowerRequest followerRequest) {
        if (userId.equals(followerRequest.getFollowerId())) {
            return Response.status(Response.Status.CONFLICT).entity("You cant follow yourself").build();
        }
        return Response.ok(this.followerService.followUser(userId, followerRequest)).build();
    }

    @DELETE
    public Response delete(@PathParam("userId") Long userId) {
        this.followerService.delete(userId);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
