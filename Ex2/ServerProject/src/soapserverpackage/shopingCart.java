package soapserverpackage;

import java.util.List;

public class shopingCart {

	public int Client_unique_id;
	public Products Cart;
	
	public void shopingCart()
	{
		this.Cart = new Products();
	}
	public shopingCart (int id,Products cart){this.Client_unique_id = id;this.Cart = cart;}
}
