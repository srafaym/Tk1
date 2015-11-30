package restserverpackge;



import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import soapserverpackage.Product;
import soapserverpackage.ProductImplementation;
import soapserverpackage.Products;
@Path("/rest")
public class RestImpl implements RestInterface {

	public RestImpl() {
		System.out.println("DDDDDDDDD");
	}
	@Override
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_XML)
	public Products getProducts() {
		// TODO Auto-generated method stub
//		List<Product> list =new ArrayList<>();
//		Product order = new Product("laptop",500,20);
//		list.add(order);
//		
//		Product order2 = new Product("headset",10,15);
//		list.add(order2);
//		
//		Product order3 = new Product("TV",300,50);
//		list.add(order3);
		
		ProductImplementation productImplementation = new ProductImplementation();
		
		
		
		System.out.println("getProducts" );
		return productImplementation.alldetails();
		
	}

	@Override
	public void addCart(Product orders) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delCart(Product orders) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
}
