package soapserverpackage;


import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Products {
public List<Product> productList;
	
	public Products(List<Product> productList){
		this.productList = productList;
	}
	public Products(){
		productList = new ArrayList<Product>();
	}

}
