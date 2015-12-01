package soapserverpackage;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Products {
public List<Product> productList;
	
	public Products(List<Product> productList){
		this.productList = productList;
	}
	public Products(){
		productList = new ArrayList<Product>();
	}

}
