import java.util.Collection;
import java.util.LinkedList;

import common.Product;
import common.Products;
import common.ShoppingCart;

public interface Client {

	LinkedList<Product> getProductsFromServer();
	
	void addToCart(String product, int amount);
		
	void notifyServerWithBuy(int client_id);
	
	boolean checkItemInStore(Product product);

	int getId();

	boolean checkAllItemsInStore(ShoppingCart cart);
	
}
