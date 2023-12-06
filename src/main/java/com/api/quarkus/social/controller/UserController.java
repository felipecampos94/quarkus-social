package com.api.quarkus.social.controller;

import com.api.quarkus.social.model.user.UserRequest;
import com.api.quarkus.social.service.UserService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GET
    public Response findAll() {
        return Response.ok(this.userService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(this.userService.findById(id)).build();
    }

    @POST
    public Response create(UserRequest userRequest) {
        return Response.status(Response.Status.CREATED)
                .entity(this.userService.create(userRequest)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, UserRequest userRequest) {
        this.userService.update(id, userRequest);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        this.userService.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
