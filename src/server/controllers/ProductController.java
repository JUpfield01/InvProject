package server.controllers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import server.Logger;
import server.models.Product;
import server.models.services.ProductService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.Console;

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
            for (Product c: Product.products) {

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

}
