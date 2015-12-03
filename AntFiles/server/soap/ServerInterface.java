package soap;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import common.Product;
import common.Products;
import common.ShoppingCart;

@WebService
@SOAPBinding(style = Style.RPC)
public interface ServerInterface {

	@WebMethod boolean addToCart(int clientId, Product product);
	
	@WebMethod boolean buy(int clientId);

	@WebMethod Products allProducts();
	
	@WebMethod boolean checkItemInStore(Product product);

	@WebMethod boolean checkAllItemsInStore(ShoppingCart cart);
	
}
