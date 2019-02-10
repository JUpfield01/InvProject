package server.controllers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import server.Logger;
import server.models.Inventory;
import server.models.Product;
import server.models.User;
import server.models.services.InventoryService;
import server.models.services.ProductService;
import server.models.services.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;

@SuppressWarnings("unchecked")
@Path("product/")
public class ProductController {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public String listProducts() {

        Logger.log("/product/list - Getting all products from database");
        String status = ProductService.selectAllInto(Product.products);

        if (status.equals("OK")) {

            JSONArray productList = new JSONArray();
            for (Product c : Product.products) {

                System.out.println(c.getProductid());
                System.out.println(c.getImageurl());
                System.out.println(c.getInventoryid());
                System.out.println(c.getProductcost());
                System.out.println(c.getProductdescription());
                System.out.println(c.getQuantity());

                JSONObject jc = c.toJSON();

                productList.add(jc);

            }

            return productList.toString();

        } else {
            JSONObject response = new JSONObject();
            response.put("error", status);
            return response.toString();
        }

    }


    @GET
    @Path("get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getConsole(@PathParam("id") int id) {

        Logger.log("/product/get/" + id + " - Getting product details from database");

        Product c = ProductService.selectById(id);
        if (c != null) {

            JSONObject cj = c.toJSON();

            Product m = ProductService.selectById(c.getProductid());
            cj.put("product", m.getProductname());

            return cj.toString();

        } else {

            return "{'error': 'Can't find product with id " + id + "'}";

        }

    }

    @POST
    @Path("save/{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String saveProduct(@PathParam("id") int id,
                              @FormParam("productname") String name,
                              @FormParam("description") String description,
                              @FormParam("price") Float price,
                              @FormParam("quantity") int quantity,
                              @FormParam("image") String image,
                              @CookieParam("sessionToken") Cookie sessionCookie) {

        User currentUser = UserService.validateSessionCookie(sessionCookie);
        if (currentUser == null) return "Error: Invalid user session token";

        if (id == -1) {
            ProductService.selectAllInto(Product.products);
            id = Product.nextId();
            Product newProduct = new Product(id, 0, name, description, price, quantity, image);

            Inventory newInventory = new Inventory(id, currentUser.getId());
            InventoryService.insert(newInventory);

            return ProductService.insert(newProduct);

        } else {

            Product existingProduct = ProductService.selectById(id);
            if (existingProduct == null) {
                return "That product doesn't appear to exist";
            } else {
                existingProduct.setProductname(name);
                existingProduct.setProductdescription(description);
                existingProduct.setProductcost(price);
                existingProduct.setQuantity(quantity);
                existingProduct.setImageurl(image);
                return ProductService.update(existingProduct);
            }

        }
    }

    @POST
    @Path("delete/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteConsole(@PathParam("id") int id,
                                @CookieParam("sessionToken") Cookie sessionCookie) {

        User currentUser = UserService.validateSessionCookie(sessionCookie);
        if (currentUser == null) return "Error: Invalid user session token";

        Logger.log("/products/delete - Product " + id);
        Product product = ProductService.selectById(id);
        if (product == null) {
            return "That product doesn't appear to exist";
        } else {
            ProductService.deleteById(id);
            return InventoryService.deleteById(id);
        }
    }


}

