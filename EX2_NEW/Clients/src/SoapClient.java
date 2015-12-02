import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import common.Product;
import common.Products;
import common.ShoppingCart;
import soap.ServerInterface;

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
		LinkedList<Product> products = serverInterface.allProducts()
				.getProducts();

		View view = new View("SOAP", products, this);

		view.createAndShowGUI();
	}

	@Override
	public LinkedList<Product> getProductsFromServer() {
		ServerInterface serverInterface = (ServerInterface) service
				.getPort(ServerInterface.class);
		System.out.println("Client get products");
		return serverInterface.allProducts().getProducts();
	}

	@Override
	public void addToCart(String name, int quantity) {
		ServerInterface serverInterface = (ServerInterface) service
				.getPort(ServerInterface.class);
		Product prod = new Product(name, 0, quantity);
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
	public boolean checkAllItemsInStore(ShoppingCart cart) {
		ServerInterface serverInterface = (ServerInterface) service
				.getPort(ServerInterface.class);
		boolean result = serverInterface.checkAllItemsInStore(cart);
		System.out.println("Check Available: " + result);
		return result;
	}

	@Override
	public int getId() {
		return id;
	}


}
