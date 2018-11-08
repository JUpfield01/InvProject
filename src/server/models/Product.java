package server.models;

import org.json.simple.JSONObject;

import java.util.ArrayList;

public class Product {
    private int productid;
    private int inventoryid;
    private int salesid;
    private String productname;
    private String productdescription;
    private float productcost;
    private int quantity;
    private String imageurl;









    // Get IntelliJ to auto-generate a constructor, getter and setters here:

    public Product(int productid, int inventoryid, int salesid, String productname, String productdescription, float productcost, int quantity, String imageurl) {
        this.productid = productid;
        this.inventoryid = inventoryid;
        this.salesid = salesid;
        this.productname = productname;
        this.productdescription = productdescription;
        this.productcost = productcost;
        this.quantity = quantity;
        this.imageurl = imageurl;
    }

    public Product() {

    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
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

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductdescription() {
        return productdescription;
    }

    public void setProductdescription(String productdescription) {
        this.productdescription = productdescription;
    }

    public float getProductcost() {
        return productcost;
    }

    public void setProductcost(float productcost) {
        this.productcost = productcost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    public static ArrayList<Product> products = new ArrayList<>();

    public static int nextId() {
        int id = 0;
        for (Product p: products) {
            if (p.getProductid() > id) {
                id = p.getProductid();
            }
        }
        return id + 1;
    }

    @SuppressWarnings("unchecked")
    public JSONObject toJSON() {
        JSONObject j = new JSONObject();
        j.put("productid", getProductid());
        j.put("inventoryid", getInventoryid());
        j.put("salesid", getSalesid());
        j.put("productname", getProductname());
        j.put("productdescription", getProductdescription());
        j.put("productcost", getProductcost());
        j.put("quantity", getQuantity());
        j.put("imageurl", getImageurl());








        return j;
    }
}