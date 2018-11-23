package server.controllers;
import server.Logger;
import server.models.User;
import server.models.services.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

public class UserController {

    @POST
    @Path("new")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String newMessage(@FormParam("username") String username,
                             @FormParam("password1") String password1,
                             @FormParam("password2") String password2) {
        Logger.log("/user/new - Creating " + username);
        UserService.selectAllInto(User.users);


            return "Error: Can't create new user.";
        }
}