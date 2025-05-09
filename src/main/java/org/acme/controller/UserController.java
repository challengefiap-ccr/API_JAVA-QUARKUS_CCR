package org.acme.controller;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.entity.UserEntity;
import org.acme.service.UserService;
import org.eclipse.microprofile.config.inject.ConfigProperty;


@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @ConfigProperty(name = "DEBUG_MODE", defaultValue = "false")
    boolean debugMode;

    @GET
    public Response findAll(@QueryParam("page") @DefaultValue("0") Integer page,
                            @QueryParam("pageSize") @DefaultValue("10") Integer pageSize) {
        var users = userService.findAll(page, pageSize);
        return Response.ok(users).build();
    }

    @POST
    @Path("/register")
    @Transactional
    public Response createUser( UserEntity userEntity) {
        return Response.ok(userService.createUser(userEntity)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateUser(@PathParam("id") Integer userId,  UserEntity userEntity) {
        return Response.ok(userService.updateUser(userId, userEntity)).build();
    }


    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteById(@PathParam("id") Integer userId) {
        userService.deleteById(userId);
        return Response.noContent().build();
    }


    @GET
    @Path("/por-email/{email}")
    public Response getByEmail(@PathParam("email") String email) {
        UserEntity user = userService.findByEmail(email);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("User with email " + email + " not found")
                    .build();
        }
        return Response.ok(user).build();
    }
}
