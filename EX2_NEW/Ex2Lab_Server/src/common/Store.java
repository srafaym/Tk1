package common;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class Store {

	private static HashMap<String, Product> productsMap;
	private static HashMap<Integer, ShoppingCart> shoppingCarts = new HashMap<>();;

	public static HashMap<String, Product> getProductsMap() {
		if (productsMap == null) {
			fillProducts();
		}
		return productsMap;
	}

	public static LinkedList<Product> getProductsList() {
		return new LinkedList<Product>(getProductsMap().values());
	}

	public static Products getProducts() {
		System.out.println("getting products in ServerImplementation");
		return new Products(getProductsList());
	}

	public static boolean checkItemInStore(Product product) {
		System.out.println("checkItemInStore in ServerImplementation");
		Product available = getProductsMap().get(product.name);
		System.out.println("Checking item in store: " + product.name
				+ " wanted: " + product.itemCount + " available: "
				+ available.itemCount);
		return available.itemCount >= product.itemCount;
	}

	public static boolean checkAllItemsInStore(int clientId) {
		return checkAllItemsInStore(getCart(clientId));
	}

	public static boolean addToCart(int clientId, Product product) {
		System.out.println("adding to cart in ServerImplementation");
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

		if (checkAllItemsInStore(clientId)) {
			for (Product product : cart.getProducts()) {
				Product available = productsMap.get(product.name);
				System.out.println("Amount before: " + available.itemCount);
				available.setItemCount(available.itemCount - product.itemCount);
				System.out.println("Amount after: " + available.itemCount);
				System.out.println("Amount after: "
						+ productsMap.get(product.name).itemCount);
			}
			shoppingCarts.put(clientId, new ShoppingCart());
			return true;
		} else {
			System.out.println("Not enough items in store");
		}
		return false;

	}

	public static void fillProducts() {
		productsMap = new HashMap<>();
		String[] items = new String[] { "Mango", "Banana", "Cucumber", "Pear",
				"Peach", "Suthern Melon", "Coconut" };
		Random rand = new Random();
		for (int i = 0; i < items.length; i++) {
			double randomNum = rand.nextDouble() * 100;
			double randomDouble = rand.nextDouble() * 3;
			double randomPrice = Math.round(randomDouble * 100) / 100.0;
			productsMap.put(items[i], new Product(items[i], randomPrice,
					(int) randomNum));
		}
		System.out.println(productsMap.size());
	}

	public static boolean checkAllItemsInStore(ShoppingCart cart) {
		for (Product product : cart.getProducts())
			if (!checkItemInStore(product)) {
				return false;
			}
		return true;
	}
}
