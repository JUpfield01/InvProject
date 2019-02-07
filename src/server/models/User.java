package server.models;

import org.json.simple.JSONObject;
import server.Logger;
import server.models.services.UserService;

import javax.ws.rs.core.Cookie;
import java.util.ArrayList;

public class User {
    private int id;
    private String username;
    private String password;
    private String sessionToken;

    public static ArrayList<User> users = new ArrayList<>();

    public static int nextId() {
        int id = 0;
        for (User u: users) {
            if (u.getId() > id) {
                id = u.getId();
            }
        }
        return id + 1;
    }

//id assigner

    public User(int id, String username, String password, String sessionToken) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.sessionToken = sessionToken;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    //Constructor

    @SuppressWarnings("unchecked")
    public JSONObject toJSON() {
        JSONObject j = new JSONObject();
        j.put("id", getId());
        j.put("username", getUsername());
        j.put("password", getPassword());
        j.put("sessionToken", getSessionToken());

        return j;
    }

}