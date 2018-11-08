package server.models;

import org.json.simple.JSONObject;

import java.util.ArrayList;

public class Inventory {
    private int inventoryid;
    private int userid;


    public Inventory(int inventoryid, int userid) {
        this.inventoryid = inventoryid;
        this.userid = userid;
    }

    public Inventory() {

    }

    public int getInventoryid() {
        return inventoryid;
    }

    public void setInventoryid(int inventoryid) {
        this.inventoryid = inventoryid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public static ArrayList<Inventory> inventorys = new ArrayList<>();

    public static int nextId() {
        int id = 0;
        for (Inventory i: inventorys) {
            if (i.getInventoryid() > id) {
                id = i.getInventoryid();
            }
        }
        return id + 1;
    }

    @SuppressWarnings("unchecked")
    public JSONObject toJSON() {
        JSONObject j = new JSONObject();
        j.put("inventoryid", getInventoryid());
        j.put("userid", getUserid());

        return j;
    }
}








