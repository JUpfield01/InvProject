import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import server.Logger;
import server.models.Inventory;
import server.models.Product;
import server.models.services.InventoryService;
import server.models.services.ProductService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@SuppressWarnings("unchecked")
@Path("inventory/")
public class InventoryController {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public String listInventorys() {

        Logger.log("/inventory/list - Getting all inventorys from database");
        String status = InventoryService.selectAllInto(Inventory.inventorys);

        if (status.equals("OK")) {

            ProductService.selectAllInto(Product.products);

            JSONArray inventoryList = new JSONArray();
            for (Inventory c: Inventory.inventorys) {

                JSONObject jc = c.toJSON();

                for (Product m : Product.products) {
                    if (m.getInventoryid() == c.getProductid()) {
                        jc.put("manufacturer", m.getProduct());
                        break;
                    }
                }

                if (c.getImageurl()().equals("")) {
                    c.setImageurl()("/client/img/none.png");
                }

                inventoryList.add(jc);

            }

            return inventoryList.toString();

        } else {
            JSONObject response = new JSONObject();
            response.put("error", status);
            return response.toString();
        }

    }

}
