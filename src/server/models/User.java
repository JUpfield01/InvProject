package server.models;

import org.json.simple.JSONObject;

import java.util.ArrayList;

public class User {
    private String userid;
    private String username;
    private String useremail;
    private String userpassword;
    private String userdob;

    // Get IntelliJ to auto-generate a constructor, getter and setters here:

    public User(String userid, String username, String useremail, String userpassword, String userdob) {
        this.userid = userid;
        this.username = username;
        this.useremail = useremail;
        this.userpassword = userpassword;
        this.userdob = userdob;
    }

    public User() {

    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getUserdob() {
        return userdob;
    }

    public void setUserdob(String userdob) {
        this.userdob = userdob;
    }
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    public static ArrayList<User> users = new ArrayList<>();


    @SuppressWarnings("unchecked")
    public JSONObject toJSON() {
        JSONObject j = new JSONObject();
        j.put("userid", getUserid());
        j.put("username", getUsername());
        j.put("useremail", getUseremail());
        j.put("userpassword", getUserpassword());
        j.put("userdob", getUserdob());











        return j;
    }
}