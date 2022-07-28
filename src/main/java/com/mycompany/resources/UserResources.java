package com.mycompany.resources;

import com.mycompany.dao.UserDao;
import com.mycompany.entity.User;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserResources {
    
    UserDao udao = new UserDao();
    
    @GET()
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getPersonnes(@Context HttpServletRequest request) {
        
        List res = udao.findAll();
        
        if ( res == null) {
            System.out.println("ca va pas");
        }
        
        return Response.status(Response.Status.OK).entity(res).build();
    }
    
    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response postPersonne(User u, @Context HttpServletRequest request) {
        
        udao.create(u);
        
        return Response.status(Response.Status.CREATED).entity("utilisateur crée").build();
    }
    
    @Path("id-{id}")
    @DELETE
    public Response deletePersonne(@PathParam("id") int id, @Context HttpServletRequest request) {
        
        udao.delete(id);
        
        return Response.status(Response.Status.OK).entity("utilisateur supprimé").build();
    }
    
    @Path("accueil")
    @GET
    public String accueil() {
        return "accueil";
    }
}
