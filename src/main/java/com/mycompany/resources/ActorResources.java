package com.mycompany.resources;

import com.mycompany.dao.ActorDao;
import com.mycompany.dao.UserDao;
import com.mycompany.entity.Actor;
import com.mycompany.entity.User;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/actor")
public class ActorResources {

    ActorDao adao = new ActorDao();

    @Path("/{id}")
    @GET()
    @Produces({MediaType.APPLICATION_JSON})
    public Actor getActor(@PathParam("id") int Id, @Context HttpServletRequest request) {

        Actor a = adao.findById(Id);

        if (a == null) {
            throw new WebApplicationException("Actor was not found", Response.Status.NOT_FOUND);
        }

        return a;
    }

    @GET()
    @Produces({MediaType.APPLICATION_JSON})
    public Response getActors(@Context HttpServletRequest request) {

        List res = adao.findAll();

        if (res == null) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity("An error occured").build());
        }

        return Response.status(Response.Status.OK).entity(res).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response postPersonne(Actor a, @Context HttpServletRequest request) {

        try {
            adao.create(a);
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity("An error occured").build());
        }
        return Response.status(Response.Status.CREATED).entity("User successfully created").build();
    }

    @Path("id-{id}")
    @DELETE
    public Response deleteActor(@PathParam("id") int id, @Context HttpServletRequest request) {

        try {
            adao.delete(id);
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity("An error occured").build());
        }

        return Response.status(Response.Status.OK).entity("actor deleted").build();
    }

    @Path("/{id}")
    @PUT()
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response putUser(@PathParam("id") int id, Actor a) {

        try {
            adao.update(id, a);

        } catch (NotFoundException e) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND)
                            .entity("actor was not found")
                            .build()
            );
        } catch (Exception e) {
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST)
                            .entity("An error occured")
                            .build()
            );
        }

        return Response.status(Response.Status.OK).entity("Actor successfully modified").build();
    }

    @Path("accueil")
    @GET
    public String accueil() {
        return "accueil";
    }

}
