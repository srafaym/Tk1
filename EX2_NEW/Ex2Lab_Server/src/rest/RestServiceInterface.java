package rest;

import common.Product;
import common.Products;
import common.ShoppingCart;

public interface RestServiceInterface {
	boolean addToCart(int clientId, Product product);
	
	boolean buy(int clientId);

	Products allProducts();
	
	boolean checkItemInStore(Product product);

	boolean checkAllItemsInStore(ShoppingCart cart);
}
