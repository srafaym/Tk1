package restserverpackge;


import soapserverpackage.Product;
import soapserverpackage.Products;

public interface RestInterface {

	//show all product mentioning name , price and quantity available 
	
	 //getproducts --> 
	public Products getProducts(); 
	
	//manage the shopping cart using client ID
	public void  addCart(Product orders);
	
	//Order order = new Order();  on client side
	
	public void delCart(Product orders);
	
	
	//integrate web server with the restful client
	
	
	
}
