package server.models;

import org.json.simple.JSONObject;

import java.util.ArrayList;

public class SaleLookup {
    private int inventoryid;
    private int salesid;















    // Get IntelliJ to auto-generate a constructor, getter and setters here:


    public SaleLookup(int inventoryid, int salesid) {
        this.inventoryid = inventoryid;
        this.salesid = salesid;
    }

    public SaleLookup() {

    }

    public int getInventoryid() {
        return inventoryid;
    }

    public void setInventoryid(int inventoryid) {
        this.inventoryid = inventoryid;
    }

    public int getSalesid() {
        return salesid;
    }

    public void setSalesid(int salesid) {
        this.salesid = salesid;
    }

    public static ArrayList<SaleLookup> salelookups = new ArrayList<>();

    public static int nextId() {
        int id = 0;
        for (SaleLookup s: salelookups) {
            if (s.getInventoryid() > id) {
                id = s.getInventoryid();
            }
        }
        return id + 1;
    }

    @SuppressWarnings("unchecked")
    public JSONObject toJSON() {
        JSONObject j = new JSONObject();
        j.put("inventoryid", getInventoryid());
        j.put("salesid", getSalesid());














        return j;
    }
}