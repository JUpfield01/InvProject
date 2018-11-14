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
@Path("console/")
public class InventoryController {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public String listConsoles() {

        Logger.log("/console/list - Getting all consoles from database");
        String status = ProductService.selectAllInto(Product.products);

        if (status.equals("OK")) {

            JSONObject response = new JSONObject();

            Console c = ConsoleService.selectById(id);
            response.put("consoleName", c.getName());





            JSONObject response = new JSONObject();
            response.put("error", status);
            return response.toString();
        }

    }


