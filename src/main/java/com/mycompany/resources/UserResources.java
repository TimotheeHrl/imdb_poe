package com.mycompany.resources;

import com.mycompany.dao.UserDao;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserResources {
    
    UserDao udao = new UserDao();
    
    @GET()
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getAllPersonnes(@Context HttpServletRequest request) {
        List res = udao.findAll();
        if ( res == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(res).build();        
        } 
        return Response.status(Response.Status.OK).entity(res).build();
    }
    
    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response postPersonne(User u, @Context HttpServletRequest request) {
        
        udao.create(u);
               
        try {
            udao.create(u);
        } catch (Exception e){
        throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity("An error occured").build());   
        }
        return Response.status(Response.Status.CREATED)
                .entity("User successfully created")
                .build();
    }
    
    @Path("/id-{id}")
    @PUT()
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response putPersonne(@PathParam("id") long id, User utilisateur) {
        
        try{
            udao.update(id, utilisateur);
        } catch (NotFoundException e){
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

    @Path("/{id}")
    @DELETE()
    public Response deletePersonne(@PathParam("id") int userId) {
        udao.delete(userId);
        try {
            udao.delete(userId);
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
        return Response.status(Response.Status.OK).entity("User successfully deleted").build();
    }
    
    @Path("/{id}")
    @GET()
    @Produces({MediaType.APPLICATION_JSON})
    public User getPersonne(@PathParam("id") long userId, @Context HttpServletRequest request) {

        User user = udao.findById(userId);

        if (user == null) {
            throw new WebApplicationException("User was not found", Response.Status.NOT_FOUND);
        }

        return user;
    }
    

}
