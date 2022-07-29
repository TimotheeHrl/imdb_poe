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

    @Path("/getall")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson(@Context HttpServletRequest request) throws ServletException, IOException {
        GenreDao genreDao = new GenreDao();
        List<Genre> genres = genreDao.findAll();
        // entityManager.close();
        if (genres != null) {
            return Response.status(Response.Status.OK).entity(genres.toString()).build();

        } else {
            return Response.status(Response.Status.NO_CONTENT).entity("pas de genre créés").build();

        }
    }
    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response postGenre(Genre newGenre, @Context HttpServletRequest request) {

        GenreDao genreDao = new GenreDao();

        genreDao.create(newGenre);
        return Response.status(Response.Status.CREATED).entity("sucessfully created").build();
    }
    @Path("/{id}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })

    @PUT
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })

    public Response updateUser(@PathParam("id") long id,Genre genreData, @Context HttpServletRequest request) {
        GenreDao genreDao = new GenreDao();
        Genre genre = genreDao.update(id, genreData);
        return Response.status(Response.Status.OK).entity(genre).build();

    }

   /*
    @Path("{name}")
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response findMoviesByGenreName(@PathParam("name") String genreName, @Context HttpServletRequest request) {
        GenreDao genreDao = new GenreDao();
      List<Movie> genre = genreDao.findMovieByGenreName(genreName);
        return Response.status(Response.Status.OK).entity(genre).build();

    }
    */


}
