package common;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Store {

	private static HashMap<String, Product> products;
	private static HashMap<Integer, ShoppingCart> shoppingCarts = new HashMap<>();;

	public static LinkedList<Product> getProductsRaw() {
		if (products == null) {
			fillProducts();
		}
		return new LinkedList<Product>(products.values());
	}

	public static Products getProducts() {
		return new Products(getProductsRaw());
	}

	public static boolean checkItemInStore(Product product) {
		Product available = products.get(product.name);
		System.out.println("Checking item in store: " + product.name
				+ " wanted: " + product.itemCount + " available: "
				+ available.itemCount);
		return available.itemCount >= product.itemCount;
	}

	public static boolean checkAllItemsInStore(ShoppingCart cart) {
		for (Product product : cart.getProducts())
			if (!checkItemInStore(product)) {
				return false;
			}
		return true;
	}

	public static boolean addToCart(int clientId, Product product) {
		ShoppingCart cart = getCart(clientId);
		if (checkItemInStore(product)) {
			cart.add(product);
			return true;
		}
		return false;
	}

	private static ShoppingCart getCart(int clientId) {
		ShoppingCart cart;
		if (!shoppingCarts.containsKey(clientId)) {
			cart = new ShoppingCart(new LinkedList<Product>());
			shoppingCarts.put(clientId, cart);
		}
		cart = shoppingCarts.get(clientId);
		return cart;
	}

	public static boolean buy(int clientId) {
		System.out.println("BUY IN STORE");
		ShoppingCart cart = getCart(clientId);

		if (checkAllItemsInStore(cart)) {
			for (Product product : cart.getProducts()) {
				Product available = products.get(product.name);
				System.out.println("Amount before: " + available.itemCount);
				available.setItemCount(available.itemCount - product.itemCount);
				System.out.println("Amount after: " + available.itemCount);
				System.out.println("Amount after: "
						+ products.get(product.name).itemCount);
			}
			shoppingCarts.put(clientId, new ShoppingCart());
			return true;
		} else {
			System.out.println("Not enough items in store");
		}
		return false;

	}

	public static void fillProducts() {
		products = new HashMap<>();
		String[] items = new String[] { "Mango", "Banana", "Cucumber", "Pear",
				"Peach", "Suthern Melon", "Coconut" };
		Random rand = new Random();
		for (int i = 0; i < items.length; i++) {
			double randomNum = rand.nextDouble() * 100;
			double randomPrice = rand.nextDouble() * 3;
			products.put(items[i], new Product(items[i], randomPrice,
					(int) randomNum));
		}
		System.out.println(products.size());
	}
}
