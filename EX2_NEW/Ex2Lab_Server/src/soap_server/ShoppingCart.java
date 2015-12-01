package soap_server;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
	
	public int Client_unique_id;
	public ArrayList<Product> Cart;
	
	public ShoppingCart()
	{
		this.Client_unique_id = 0;
		this.Cart = new ArrayList<Product>();
	}
	public ShoppingCart(int client_id)
	{
		this.Client_unique_id = client_id;
		this.Cart = new ArrayList<Product>();
	}
	public ShoppingCart (int id,ArrayList<Product> cart){
		this.Client_unique_id = id;this.Cart = cart;
		}
	
}

