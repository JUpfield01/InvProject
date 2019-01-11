package server.controllers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import server.Logger;
import server.models.Inventory;
import server.models.Product;
import server.models.services.InventoryService;
import server.models.services.ProductService;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@SuppressWarnings("unchecked")
@Path("inventory/")
public class InventoryController {

    @Path("list/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String listInventory(@PathParam("id") int userId) {

        Logger.log("/inventory/list/{id} - Getting all products from database for user");
        String status = InventoryService.selectAllInto(Inventory.inventorys);

        if (status.equals("OK")) {

            JSONArray inventoryList = new JSONArray();

            for (Inventory i : Inventory.inventorys) {

                System.out.println(i.getUserid() + ":" + i.getProductId());

                if (userId == i.getUserid()) {

                    Product c = ProductService.selectById(i.getProductId());

                    System.out.println(c.getProductid());
                    System.out.println(c.getImageurl());
                    System.out.println(c.getInventoryid());
                    System.out.println(c.getProductcost());
                    System.out.println(c.getProductdescription());
                    System.out.println(c.getQuantity());

                    JSONObject jc = c.toJSON();

                    inventoryList.add(jc);

                }
            }

            return inventoryList.toString();

        } else {
            JSONObject response = new JSONObject();
            response.put("error", status);
            return response.toString();
        }

    }

}
