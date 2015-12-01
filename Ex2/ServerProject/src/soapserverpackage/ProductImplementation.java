package soapserverpackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.jws.WebService;

@WebService(endpointInterface = "soapserverpackage.ProductInterface")
public class ProductImplementation implements ProductInterface {

	public static ArrayList<Product> listProduct;
	public static Products products  ;
	public static ArrayList<shopingCart> shopingCartList;

	public ProductImplementation (){
		
	shopingCartList = new ArrayList<shopingCart>();
		// TODO Auto-generated method stub
	String[] items  = {"Mango", "Banana", "Cucumber", "Pear", "Peach", "Suthern Melon", "Coconut"};
	listProduct = new ArrayList<Product>();
	Random rand = new Random();
	for (int i = 0; i < items.length; i++) {
		
		int randomNum = rand.nextInt(((items.length-1) - 0) + 1) + 0;
		
		listProduct.add( new Product(1,items[i],(double)rand.nextInt((20 - 0) + 1) + 0,  rand.nextInt((5 - 1) + 1) + 1) );
	}
			
	products = new Products(listProduct);
	}
	
	@Override
	public Products alldetails() {
		return products;
	}

	public Boolean CheckItemInstore(Product client_request_cart)
	{
		for (Product pMain : products.productList) {
			if (pMain.itemname == client_request_cart.itemname) {
				if (pMain.available_in_store < client_request_cart.available_in_store)
					{return false;}
			}
		}
		
		return true;
	}
	@Override
	public String addtocart(int client_id,Product client_request_item) {
		String message = "no message for you fuck face";
		
		boolean clientfound = false;
		//create first client
		if (shopingCartList.size() == 0) {
			shopingCart new_cart = new shopingCart(client_id,new Products() );
			shopingCartList.add(new_cart); }
			
			
		//if client exists find it
		int shopingCart_index = 0;
		for (shopingCart cart_client : shopingCartList) {
			if (cart_client.Client_unique_id == client_id)
			{
				clientfound = true;
				//check item in store
				if (CheckItemInstore(client_request_item)){
				//add that item to client shopping cart
					cart_client.Cart.productList.add(client_request_item);
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
			shopingCart new_cart = new shopingCart(client_id,new Products() );
			
			//check item in store
			if (CheckItemInstore(client_request_item)){
			//add that item to client shopping cart
				new_cart.Cart.productList.add(client_request_item);
				message = String.format("Item : %s .Is Added to your cart",client_request_item.itemname);
				}
			else{
			message = String.format("Item : %s .Required amount is not in store at the moment",client_request_item.itemname);
			}
			
			shopingCartList.add(new_cart);
		}
			
	return message;	
	}
	@Override
	public String Buy(int client_id)
	{
		String msg = "Client not found";
		for (shopingCart cart_client : shopingCartList) {
			if (cart_client.Client_unique_id == client_id){
				msg = cart_client.Cart.productList.toString();}
			}
		return msg;
	}

}

