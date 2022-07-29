package com.mycompany.auth;

import com.mycompany.auth.BasicAuth;
import com.mycompany.dao.UserDao;
import com.mycompany.entity.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@PreMatching
public class AuthentificationFilter implements ContainerRequestFilter {

    @Context
    public HttpServletRequest request;
    
    UserDao udao = new UserDao();
    
    @Override
    public void filter(ContainerRequestContext containerRequest) throws IOException {
        // Get the authentification passed in HTTP headers parameters
        String auth = containerRequest.getHeaderString("Authorization");
        
        
        // If the user does not have the right (does not provide any HTTP Basic Auth)
        if (auth == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .entity("You must be connected 0")
                            .build()
            );
        }

        // lap : loginAndPassword
        String[] lap = BasicAuth.decode(auth);
 
        if (lap == null || lap.length != 2) {
            throw new WebApplicationException(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .entity("You must be connected 1")
                            .build()
            );
        }

        User authentificationResult = checkUser(lap[0], lap[1]);
        
        System.out.println(authentificationResult);
        if (authentificationResult == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .entity("You must be connected 2")
                            .build()
            );
        }
 
        request.setAttribute("user", authentificationResult);
    }


    
   public User checkUser(String email, String password) {
        List<User> liste = udao.findAll();

        for (User user : liste) {
            if (user.getEmail().equals(email)) {
                if (user.getPassword().equals(password)) {
                    return user;
                }
            }
        }
        return null;
    }
}
