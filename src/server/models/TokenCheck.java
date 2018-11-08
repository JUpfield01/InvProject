package server.models;

import org.json.simple.JSONObject;

import java.util.ArrayList;

public class TokenCheck {
    private String username;
    private String password;
    private String sessiontoken;














    // Get IntelliJ to auto-generate a constructor, getter and setters here:

    public TokenCheck(String username, String password, String sessiontoken) {
        this.username = username;
        this.password = password;
        this.sessiontoken = sessiontoken;
    }

    public TokenCheck() {

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

    public String getSessiontoken() {
        return sessiontoken;
    }

    public void setSessiontoken(String sessiontoken) {
        this.sessiontoken = sessiontoken;
    }
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    public static ArrayList<TokenCheck> tokenchecks = new ArrayList<>();


    @SuppressWarnings("unchecked")
    public JSONObject toJSON() {
        JSONObject j = new JSONObject();
        j.put("username", getUsername());
        j.put("password", getPassword());
        j.put("sessiontoken", getSessiontoken());













        return j;
    }
}