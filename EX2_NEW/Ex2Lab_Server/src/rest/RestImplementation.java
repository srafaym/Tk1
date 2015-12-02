package rest;

import javax.xml.bind.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;

import com.oracle.webservices.internal.api.databinding.DatabindingMode;

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
		System.out.println("getting products in ServerImplementation");
		Products products = Store.getProducts();
		System.out.println("products.size: " + products.getProducts().size());
		return products;
	}

	@Override
	public boolean addToCart(int clientId, Product product) {
		return Store.addToCart(clientId, product);
	}

	@Override
	public boolean buy(int clientId) {
		return Store.buy(clientId);
	}

	@Override
	public boolean checkItemInStore(Product product) {
		return Store.checkItemInStore(product);
	}

	@Override
	public boolean checkAllItemsInStore(ShoppingCart cart) {
		return Store.checkAllItemsInStore(cart);
	}

	private String marshal(Products products) throws IOException {
		StringBuilder sb = new StringBuilder();
		try {
			// File file = new File("C:\\file.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Products.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(
					javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true);

			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			jaxbMarshaller.marshal(products, stream);
			stream.flush();

			sb.append(stream);

			jaxbMarshaller.marshal(products, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

	//
	// @Override
	// public String addtocart(@PathParam("a") int client_id,
	// @DatabindingMode("b") Product b) {
	// // System.out.println("Calling addtocart from Rest Client "+ client_id);
	// // ProductImplementation pImpl = new ProductImplementation();
	// // System.out.println(pImpl.addtocart(client_id, b));
	// return null;
	// }
	//
	// @Override
	// public String Buy(int client_id) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// @GET
	// @Path("/add/{a}/{b}/{c}/{d}/{e}/{f}")
	// @Produces(MediaType.APPLICATION_XML)
	// public String getClientData(@PathParam("a") int client_id,
	// @PathParam("b") String itemname,
	// @PathParam("c") double price, @PathParam("d") int available_in_store,
	// @PathParam("e") int item_id,
	// @PathParam("f") int item_unique_id) {
	//
	// System.out.println("Client ID "+client_id);
	// System.out.println("Product [itemname=" + itemname + ", price=" + price +
	// ", available_in_store=" + available_in_store
	// + ", item_id=" + item_id + ", item_unique_id=" + item_unique_id + "]");
	//
	// //whatever data client passed we will make it part of product by make new
	// Product.
	//
	// Product product = new Product(item_unique_id, itemname, price,
	// available_in_store);
	// ProductImplementation pImpl = new ProductImplementation();
	// System.out.println(pImpl.addtocart(client_id, product));
	// return pImpl.addtocart(client_id, product);
	// }

}
