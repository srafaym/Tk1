package common;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Products {

	public LinkedList<Product> products;

	public Products() {
		System.out.println("Products default constructor");
		products = Store.getProductsRaw();
		System.out.println(products.get(0).name + "  " +products.get(0).itemCount);
	}

	public Products(LinkedList<Product> products) {
		System.out.println("Products non-default constructor " + products.size());
		System.out.println(products.get(0).name + "  " +products.get(0).itemCount);
		this.products = products;
	}

	public LinkedList<Product> getProducts() {
		System.out.println("Server get products");
		return products;
	}
}
