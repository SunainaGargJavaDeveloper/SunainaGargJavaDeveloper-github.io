

package edu.npu.ShopperShop.resthandler;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import edu.npu.ShopperShop.Services.InventoryService;
import edu.npu.ShopperShop.Domain.*;

@Path("/")
public class ProductRestHandler {
	@Autowired
	private InventoryService inventservice;
	//private Logger logger = Logger.getLogger(StudentResthandler.class);

@GET
@Path("/items")
@Produces("application/xml")
public ProductList getItemList() {
	List<Product> prodList;
	ProductList listOfProducts = new ProductList();
	
	prodList = inventservice.printCurrentInventory();
	listOfProducts.setPList(prodList);
	return listOfProducts;
}
@POST
@Path("/insert")
@Produces("application/json, application/xml")
@Consumes("application/json, application/xml")
public Response insertProduct(Product prod){
	ResponseBuilder  respbuilder;
	inventservice.insertInventory(prod);
	respbuilder=Response.status(Status.CREATED);
	respbuilder.entity(prod);
	return respbuilder.build();
}

@DELETE
@Path("/delete/{id}")
public Response deleteProduct(@PathParam("id") int id) {
     int removedprod;
     System.out.println(id);
     ResponseBuilder respBuilder;
     removedprod=inventservice.deleteInventory(id);
     if (removedprod == 0) {
			respBuilder = Response.status(Status.NOT_FOUND);
		} else {
			respBuilder = Response.ok();
		}
		return respBuilder.build();

}

@PUT
@Path("/Update/{id}/quantity/{quantity}")
@Produces("application/json, application/xml")
@Consumes("application/json, application/xml")
public Response updateProduct(@PathParam("id") int id,@PathParam("quantity") int quantity) {
    
    ResponseBuilder respBuilder;
    int updatedRow=inventservice.updateInventory(id, quantity);
    if (updatedRow == 0) {
			respBuilder = Response.status(Status.NOT_FOUND);
		} else {
			respBuilder = Response.ok();
		}
    Product updatedProduct=inventservice.getproduct(id);
	respBuilder.entity(updatedProduct);
    return respBuilder.build();

}
}