package com.app.controller;

;
import com.app.domain.User;
import com.app.service.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/user")
@Produces(MediaType.TEXT_HTML)
public class UserController {

    @Inject
    UserService userService;

    @POST
    @Path("/{username}")
    public Response createUser(@PathParam("username") String username) {
        userService.createUser(username);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("/{username}")
    public Response getUserActionsPage(@PathParam("username") String username) {
        User user = userService.getUser(username);
        if (user != null) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}