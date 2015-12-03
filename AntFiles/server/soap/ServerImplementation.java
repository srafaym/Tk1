package soap;

import javax.jws.WebService;

import common.Product;
import common.Products;
import common.ShoppingCart;
import common.Store;

@WebService(endpointInterface = "soap.ServerInterface")
public class ServerImplementation implements ServerInterface {

	public ServerImplementation() {

	}

	@Override
	public Products allProducts() {
		System.out.println("getting products in ServerImplementation");
		return Store.getProducts();
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
}
