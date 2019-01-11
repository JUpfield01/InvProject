package server.models;

import org.json.simple.JSONObject;

import java.util.ArrayList;

public class Inventory {
    private int productId;
    private int userid;


    public Inventory(int inventoryid, int userid) {
        this.productId = inventoryid;
        this.userid = userid;
    }

    public Inventory() {

    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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
            if (i.getProductId() > id) {
                id = i.getProductId();
            }
        }
        return id + 1;
    }

    @SuppressWarnings("unchecked")
    public JSONObject toJSON() {
        JSONObject j = new JSONObject();
        j.put("productId", getProductId());
        j.put("userid", getUserid());

        return j;
    }
}








