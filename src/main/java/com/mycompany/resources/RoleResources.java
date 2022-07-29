package com.mycompany.resources;

import com.mycompany.dao.RoleDao;
import com.mycompany.dao.UserDao;
import com.mycompany.entity.Role;
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

@Path("/role")
public class RoleResources {
    
    
    RoleDao rdao = new RoleDao();
    
    @GET()
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getRole(@Context HttpServletRequest request) {
        
        try {
            rdao.findAll();
        } catch (Exception e) {
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST)
                            .entity("An error occured")
                            .build()
            );
        }
        
        return Response.status(Response.Status.OK).entity(rdao.findAll()).build();
    }
    
    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response postRole(Role r, @Context HttpServletRequest request) {
        
        try {
            rdao.create(r);
        } catch (Exception e) {
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST)
                            .entity("An error occured")
                            .build()
            );
        }
        
        return Response.status(Response.Status.CREATED).entity("Role successfully created").build();
    }
    
    @Path("id-{id}")
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response putRole(@PathParam("id") int id, Role r) {
        
        System.out.println(id);
        System.out.println(r.getDesciption());
        System.out.println(r.getIdentifiant());
        
        try {
            rdao.update(id, r);

        } catch (NotFoundException e) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND)
                            .entity("Role was not found")
                            .build()
            );
        } catch (Exception e) {
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST)
                            .entity("An error occured")
                            .build()
            );
        }

        return Response.status(Response.Status.OK).entity("Role successfully modified").build();
    }
    
    
    @Path("id-{id}")
    @DELETE
    public Response deleteRole(@PathParam("id") int id, @Context HttpServletRequest request) {
        
        try {
            rdao.delete(id);
        } catch (NotFoundException e) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND)
                            .entity("Role was not found")
                            .build()
            );
        } catch (Exception e) {
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST)
                            .entity("An error occured")
                            .build()
            );
        }
        
        return Response.status(Response.Status.OK).entity("Role successfully deleted").build();
    }
    
    @Path("/{id}")
    @GET()
    @Produces({MediaType.APPLICATION_JSON})
    public Role getRole(@PathParam("id") int id, @Context HttpServletRequest request) {

        Role r = rdao.findById(id);

        if (r == null) {
            throw new WebApplicationException("Role was not found", Response.Status.NOT_FOUND);
        }

        return r;
    }
    
    @Path("accueil")
    @GET
    public String accueil() {
        return "accueil";
    }
}