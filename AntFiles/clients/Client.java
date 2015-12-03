import java.util.Collection;

import java.util.LinkedList;



import soap.Product;

import soap.Products;





public interface Client {



	LinkedList<Product> getProductsFromServer();

	

	void addToCart(String product, int amount);

		

	void notifyServerWithBuy(int client_id);

	

	boolean checkItemInStore(Product product);



	int getId();



	

}

