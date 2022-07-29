
package com.mycompany.resources;

import com.mycompany.dao.MovieDAO;
import com.mycompany.entity.Movie;
import java.util.List;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("movie")
public class MovieResources {
    
    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Movie> getAllMovies() {
        MovieDAO mdao = new MovieDAO();
        return mdao.findAll();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Movie getMovie(@PathParam("id") long id) {
        MovieDAO mdao = new MovieDAO();
        Movie movie = mdao.findById(id);

        if (movie == null) {
            throw new WebApplicationException("Movie was not found", Response.Status.NOT_FOUND);
        }
        return movie;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response postMovie(Movie newMovie) {
        MovieDAO udao = new MovieDAO();

        try {
            udao.create(newMovie);
        } catch (Exception e) {
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST)
                            .entity("An error occured")
                            .build()
            );
        }

        return Response.status(Response.Status.CREATED).entity(newMovie).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response updateMovie(Movie movie, @PathParam("id") long id) {
        MovieDAO mdao = new MovieDAO();

        try {
            mdao.update(id, movie);
        } catch (NotFoundException e) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND)
                            .entity("Movie was not found.")
                            .build()
            );
        } catch (Exception e) {
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST)
                            .entity("An error occured.")
                            .build());
        }
        return Response.status(Response.Status.OK).entity("This update is a success").build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteMovie(@PathParam("id") long id) {
        MovieDAO udao = new MovieDAO();
        try {
            udao.delete(id);
        } catch (NotFoundException e) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND)
                            .entity("Movie was not found.")
                            .build()
            );
        } catch (Exception e) {
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST)
                            .entity("An error occured.")
                            .build());
        }
        return Response.status(Response.Status.OK).entity("The movie is removed.").build();
    }
    
    
    
}
