package common;

import java.util.LinkedList;

public class ShoppingCart {
	public LinkedList<Product> products;

	public ShoppingCart() {
		products = new LinkedList<>();
		System.out.println("ShoppingCart default constructor");
	}

	public ShoppingCart(LinkedList<Product> products) {
		System.out.println("ShoppingCart non-default constructor "
				+ products.size());
		if (products.size() > 0) {
			System.out.println(products.get(0).name + "  "
					+ products.get(0).itemCount);
		}
		this.products = products;
	}

	public LinkedList<Product> getProducts() {
		System.out.println("Server get products");
		return products;
	}

	public void add(Product product) {
		this.products.add(product);
	}
}
