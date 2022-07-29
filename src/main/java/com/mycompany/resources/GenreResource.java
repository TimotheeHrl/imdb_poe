package com.mycompany.resources;

import com.mycompany.dao.GenreDao;
import com.mycompany.entity.Genre;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@Path("/genre")
public class GenreResource {

    GenreDao genreDao = new GenreDao();

    @Path("/getall")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson(@Context HttpServletRequest request) throws ServletException, IOException {

        List<Genre> genres = genreDao.findAll();
        if (genres != null) {
            return Response.status(Response.Status.OK).entity(genres.toString()).build();

        } else {
            return Response.status(Response.Status.NO_CONTENT).entity("pas de genre créés").build();

        }
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response postGenre(Genre newGenre, @Context HttpServletRequest request) {

        genreDao.create(newGenre);
        return Response.status(Response.Status.CREATED).entity("sucessfully created").build();
    }

    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @PUT
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateUser(@PathParam("id") int id, Genre genreData, @Context HttpServletRequest request) {
        Genre genre = genreDao.update(id, genreData);
        return Response.status(Response.Status.OK).entity(genre).build();
    }

    @Path("/{id}")
    @DELETE()
    public Response deleteGenre(@PathParam("id") int id) {
        try {
            genreDao.delete(id);
        } catch (NotFoundException e) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND)
                            .entity("User was not found")
                            .build()
            );
        } catch (Exception e) {
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST)
                            .entity("An error occured")
                            .build()
            );
        }
        return Response.status(Response.Status.OK).entity("genresuccessfully deleted").build();
    }

    @Path("/id-{id}")
    @PUT()
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response putPersonne(@PathParam("id") int id, Genre genre) {
        try {
            genreDao.update(id, genre);
        } catch (NotFoundException e) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND)
                            .entity("User was not found")
                            .build());
        } catch (Exception e) {
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST)
                            .entity("An error occured")
                            .build()
            );
        }
        return Response.status(Response.Status.OK).entity("User successfully modified").build();
    }

}
