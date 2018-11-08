package server.models;

import org.json.simple.JSONObject;

import java.util.ArrayList;

public class Sale {
    private int salesid;
    private int productid;
    private int amountsold;














    // Get IntelliJ to auto-generate a constructor, getter and setters here:

    public Sale(int salesid, int productid, int amountsold) {
        this.salesid = salesid;
        this.productid = productid;
        this.amountsold = amountsold;
    }

    public Sale() {

    }

    public int getSalesid() {
        return salesid;
    }

    public void setSalesid(int salesid) {
        this.salesid = salesid;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public int getAmountsold() {
        return amountsold;
    }

    public void setAmountsold(int amountsold) {
        this.amountsold = amountsold;
    }
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    public static ArrayList<Sale> sales = new ArrayList<>();

    public static int nextId() {
        int id = 0;
        for (Sale s: sales) {
            if (s.getSalesid() > id) {
                id = s.getSalesid();
            }
        }
        return id + 1;
    }

    @SuppressWarnings("unchecked")
    public JSONObject toJSON() {
        JSONObject j = new JSONObject();
        j.put("salesid", getSalesid());
        j.put("productid", getProductid());
        j.put("amountsold", getAmountsold());













        return j;
    }
}