import java.net.MalformedURLException;
import java.net.URL;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;


import soap.ServerInterface;

import soap.Product;
import soap.Products;
import soap.ShoppingCart;

public class SoapClient implements Client {

	public View view;
	public int id;
	public Service service;

	public SoapClient(int id) {
		this.id = id;
		URL url = null;
		try {
			url = new URL("http://localhost:8090/soap?wsdl");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 1st argument service URI, refer to wsdl document above
		// 2nd argument is service name, refer to wsdl document above
		QName qname = new QName("http://soap/", "ServerImplementationService");

		service = Service.create(url, qname);

		ServerInterface serverInterface = (ServerInterface) service
				.getPort(ServerInterface.class);
		LinkedList<Product> products = (LinkedList<Product>) serverInterface.allProducts()
				.getProducts();

		View view = new View("SOAP", products, this);

		view.createAndShowGUI();
	}

	@Override
	public LinkedList<Product> getProductsFromServer() {
		ServerInterface serverInterface = (ServerInterface) service
				.getPort(ServerInterface.class);
		System.out.println("Client get products");
		return (LinkedList<Product>) serverInterface.allProducts().getProducts();
	}

	@Override
	public void addToCart(String name, int quantity) {
		
		
		ServerInterface serverInterface = (ServerInterface) service
				.getPort(ServerInterface.class);
		Product prod = new Product();
		prod.setName(name);
		prod.setItemCount(quantity);
		boolean result = serverInterface.addToCart(id, prod);
		System.out.println("Add to cart: " + result);
	}

	@Override
	public void notifyServerWithBuy(int clientId) {
		ServerInterface serverInterface = (ServerInterface) service
				.getPort(ServerInterface.class);
		boolean result = serverInterface.buy(clientId);
		System.out.println("Notify Server With Buy at client: " + result);
	}

	@Override
	public boolean checkItemInStore(Product product) {
		ServerInterface serverInterface = (ServerInterface) service
				.getPort(ServerInterface.class);
		boolean result = serverInterface.checkItemInStore(product);
		System.out.println("Check Available: " + result);
		return result;
	}

	@Override
	public int getId() {
		return id;
	}


}
