package com.mariiapasichna.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mariiapasichna.dao.UserDao;
import com.mariiapasichna.model.User;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/")
public class UserApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<User> users = UserDao.getInstance().getAll();
        String json = gson.toJson(users);
        return Response
                .status(Response.Status.OK)
                .entity(json)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/add")
    public Response addUser(@FormParam("name") String name, @FormParam("age") int age) {
        UserDao.getInstance().addUser(new User(name, age));
        return Response
                .status(Response.Status.OK)
                .entity("User added")
                .build();
    }
}