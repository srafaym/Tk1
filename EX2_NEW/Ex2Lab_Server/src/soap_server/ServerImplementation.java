package soap_server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.jws.WebService;

@WebService(endpointInterface = "soap_server.ServerInterface")
public class ServerImplementation implements ServerInterface {

	public static ArrayList<Product> MainProductsList;
	public static ArrayList<ShoppingCart> shopingCartList;
	
	public void ServerImplementation() {
	
	shopingCartList = new ArrayList<ShoppingCart>();
		// TODO Auto-generated method stub
	String[] items  = {"Mango", "Banana", "Cucumber", "Pear", "Peach", "Suthern Melon", "Coconut"};
	MainProductsList = new ArrayList<Product>();
	Random rand = new Random();
	for (int i = 0; i < items.length; i++) {
		
		int randomNum = rand.nextInt(((items.length-1) - 0) + 1) + 0;
		
		MainProductsList.add( new Product(1,items[i],(double)rand.nextInt((20 - 0) + 1) + 0,  rand.nextInt((5 - 1) + 1) + 1) );
	}

	}
	public ArrayList<Product> alldetails() {
		// TODO Auto-generated method stub
		return MainProductsList;
	}

	public Boolean CheckItemInstore(Product client_request_cart)
	{
		for (Product pMain : MainProductsList) {
			if (pMain.itemname == client_request_cart.itemname) {
				if (pMain.available_in_store < client_request_cart.available_in_store)
					{return false;}
			}
		}
		
		return true;
	}
	
	public String addtocart(int client_id, Product client_request_item) {
String message = "no message for you fuck face";
		
		boolean clientfound = false;
		//create first client
		if (shopingCartList.size() == 0) {
			ShoppingCart new_cart = new ShoppingCart(client_id);
			shopingCartList.add(new_cart); }
			
			
		//if client exists find it
		int shopingCart_index = 0;
		for (ShoppingCart cart_client : shopingCartList) {
			if (cart_client.Client_unique_id == client_id)
			{
				clientfound = true;
				//check item in store
				if (CheckItemInstore(client_request_item)){
				//add that item to client shopping cart
					cart_client.Cart.add(client_request_item);
					shopingCartList.remove(shopingCart_index);
					shopingCartList.add(cart_client);
					
					message = String.format("Item : %s .Is Added to your cart",client_request_item.itemname);
					break;
				}
				
				else{
				message = String.format("Item : %s .Required amount is not in store at the moment",client_request_item.itemname);
				}
			}
			shopingCart_index++;
			}
		if (!clientfound){
			//create client first
			ShoppingCart new_cart = new ShoppingCart(client_id );
			
			//check item in store
			if (CheckItemInstore(client_request_item)){
			//add that item to client shopping cart
				new_cart.Cart.add(client_request_item);
				message = String.format("Item : %s .Is Added to your cart",client_request_item.itemname);
				}
			else{
			message = String.format("Item : %s .Required amount is not in store at the moment",client_request_item.itemname);
			}
			
			shopingCartList.add(new_cart);
		}
			
	return message;	
	}

	public String Buy(int client_id) {
		String msg = "Client not found";
		for (ShoppingCart cart_client : shopingCartList) {
			if (cart_client.Client_unique_id == client_id){
				msg = cart_client.Cart.toString();}
			}
		return msg;
	}

}
