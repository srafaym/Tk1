package rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import common.Product;
import common.Products;
import common.ShoppingCart;
import common.Store;

@Path("/rest")
public class RestImplementation implements RestServiceInterface {

	@Override
	@GET
	@Path("/products")
	@Produces(MediaType.APPLICATION_XML)
	public Products allProducts() {
		Products products = Store.getProducts();
		System.out.println("pears: " + products.getProducts().get(0).itemCount);
		return products;
	}

	@Override
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_XML)
	public Response addToCart(@QueryParam("clientId") int clientId, Product product) {
		boolean result = Store.addToCart(clientId, product);
		return Response.status(201).entity(result + "").build();
	}

	@Override
	@POST
	@Path("/buy")
	public Response buy(@QueryParam("clientId") int clientId) {
		boolean result = Store.buy(clientId);
		return Response.status(201).entity(result + "").build();
	}

	@Override
	@POST
	@Consumes({ MediaType.APPLICATION_XML })
	@Path("/check")
	public Response checkItemInStore(Product product) {
		boolean result = Store.checkItemInStore(product);
		return Response.status(201).entity(result + "").build();
	}

	@Override
	@GET
	@Path("/checkAll")
	@Produces(MediaType.APPLICATION_XML)
	public Response checkAllItemsInStore(@QueryParam("clientId") int clientId) {
		System.out.println("checkItemInStore in ServerImplementation");
		boolean result = Store.checkAllItemsInStore(clientId);
		return Response.status(201).entity(result + "").build();
	}

}
