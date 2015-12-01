package restserverpackge;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.oracle.webservices.internal.api.databinding.DatabindingMode;

import soapserverpackage.Product;
import soapserverpackage.ProductImplementation;
import soapserverpackage.ProductInterface;
import soapserverpackage.Products;

@Path("/rest")
public class RestImpl implements ProductInterface {

	@Override
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Products alldetails() {
		ProductImplementation productImplementation = new ProductImplementation();
		return productImplementation.alldetails();
	}

	@Override
	@POST
	@Path("/add/{a}/{b}")
	@Produces(MediaType.APPLICATION_XML)
	public String addtocart(@PathParam("a") int client_id, @DatabindingMode("b") Product b) {
		// System.out.println("Calling addtocart from Rest Client "+ client_id);
		// ProductImplementation pImpl = new ProductImplementation();
		// System.out.println(pImpl.addtocart(client_id, b));
		return null;
	}

	@Override
	public String Buy(int client_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@GET
	@Path("/add/{a}/{b}/{c}/{d}/{e}/{f}")
	@Produces(MediaType.APPLICATION_XML)
	public String getClientData(@PathParam("a") int client_id, @PathParam("b") String itemname,
			@PathParam("c") double price, @PathParam("d") int available_in_store, @PathParam("e") int item_id,
			@PathParam("f") int item_unique_id) {
		
		System.out.println("Client ID "+client_id);
		System.out.println("Product [itemname=" + itemname + ", price=" + price + ", available_in_store=" + available_in_store
		+ ", item_id=" + item_id + ", item_unique_id=" + item_unique_id + "]");
		Product product = new Product(item_unique_id, itemname, price, available_in_store);
		ProductImplementation pImpl = new ProductImplementation();
		System.out.println(pImpl.addtocart(client_id, product));
		return pImpl.addtocart(client_id, product);
	}

}
