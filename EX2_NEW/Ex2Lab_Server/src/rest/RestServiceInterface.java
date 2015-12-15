package rest;

import javax.ws.rs.core.Response;

import common.Product;
import common.Products;
import common.ShoppingCart;

public interface RestServiceInterface {
	Response addToCart(int clientId, Product product);
	
	Response buy(int clientId);

	Products allProducts();
	
	Response checkItemInStore(Product product);

	Response checkAllItemsInStore(int clientId);
}
