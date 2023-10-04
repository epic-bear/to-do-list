package com.app.controller;

import com.app.domain.Action;
import com.app.service.ActionService;
import com.app.service.UserService;
import io.quarkus.qute.Template;
import io.smallrye.common.annotation.Blocking;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/actions")
@Produces(MediaType.TEXT_HTML)
@Blocking
public class ActionController {

    @Inject
    Template actions;

    @Inject
    UserService userService;

    @Inject ActionService actionService;

    @GET
    public String getActionsPage(@QueryParam("username") String username) {
        List<Action> userActions = userService.getUserActions(username);
        return actions.data("actions", userActions).data("username", username).render();
    }

    @POST
    @Transactional
    public Response createAction( @QueryParam("username") String username, @QueryParam("description") String description) {
        actionService.createAction(username, description);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateAction(@PathParam("id") Long id, @Valid Action updatedAction) {
        Action updated = actionService.updateAction(id, updatedAction);
        if (updated == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(updated).build();
    }
}
