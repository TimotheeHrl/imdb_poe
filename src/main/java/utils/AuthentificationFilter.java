package utils;

import com.mycompany.dao.UserDao;
import com.mycompany.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;

@Provider
@PreMatching
public class AuthentificationFilter implements ContainerRequestFilter {

    @Context
    HttpServletRequest request;

   public int userIdThatIsLoggedIn ;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        // Récuperer le header Authorization
        // Décoder la valeur du header Authorization pour récuperer l'email et le mot de passe
        // Implémenter la méthode checkUser qui vérifie les identifiants
        // Mettre l'utilisateur en attribut de la requete
        MultivaluedMap<String, String> testHeader = requestContext.getHeaders();
        String authHeader = testHeader.getFirst("Authorization");
        System.out.println("authHedar" + " " +authHeader);



        String[] decodedAuth = BasicAuth.decode(authHeader);

        String email = decodedAuth[0]; // email
        String password = decodedAuth[1]; // password
        System.out.println("email" +" "+ email +"password" + " "+ password);
        int userFound =  checkUser(email,password);
        System.out.println("-----------userFound--------"+ userFound);
        
                if(userFound == -1){
      throw new WebApplicationException( Response.status(401).entity("Bad credentials..").build());
          }

    }

    public int checkUser(String email, String password) {
        int idFound = -1;
        UserDao userDao = new UserDao();
        List<User> users = userDao.findAll();

        for(int i = 0; i < users.size(); i++){
           User userFromList = users.get(i);
            String valEmail = userFromList.getEmail();

            Boolean emailMatch = valEmail.equals(email);
           Boolean passwordMatch = userFromList.getPassword().equals(password) ;

            System.out.println("userFromList.getEmail()" + " "+ valEmail+" "+ "/"+ " " + email+" "+ "emailMatch"+ " "+ emailMatch);
            System.out.println( "userFromList.getPassword"+ " "+"userFromList.getPassword" +" "+userFromList.getPassword() +" "+"password"+password+" "+  "passwordMatch"+ " " +passwordMatch);
            if(userFromList.getEmail().equals(email) && userFromList.getPassword().equals(password) ){
                idFound = userFromList.getIdUser();
                System.out.println("-------------idFound------------IN----LOOP-----"+idFound);
                break;
            }
        }
        if(idFound == -1){
            System.out.println("pas trouver");
        }
       
      //  System.out.println("-------------idFound------------AT----THE--END---"+idFound);

        return idFound;
    }



}