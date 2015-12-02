import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import common.Product;
import common.Products;
import common.ShoppingCart;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class RestClient implements Client {

	private View view;
	private LinkedList<Product> products;
	static final String REST_URI = "http://localhost:8080/server";
	static final String ADD_PATH = "rest/products";
	// static final String ADD_TO_CART ="rest/add";
	WebResource service;

	private int id;

	public RestClient(int id) {
		ClientConfig config = new DefaultClientConfig();

		com.sun.jersey.api.client.Client client = com.sun.jersey.api.client.Client
				.create(config);
		this.service = client.resource(REST_URI);
		this.id = id;
		this.products = getProductsFromServer();
		view = new View("Rest", products, this);
		view.createAndShowGUI();
	}

	@Override
	public LinkedList<Product> getProductsFromServer() {
		WebResource orderService = service.path(ADD_PATH);
		String marshalled = getOutputAsAppXml(orderService);
		System.out.println("orderService Response: "
				+ orderService.getURI().toString() + " : " + marshalled);

		return demarshal(marshalled);
	}

	public LinkedList<Product> demarshal(String source) {
		//TODO
		LinkedList<Product> products = new LinkedList<>();
//		try {
//			// File file = new File("C:\\file.xml");
//			JAXBContext jaxbContext = JAXBContext.newInstance(Products.class);
//			Unmarshaller jaxbMarshaller = jaxbContext.createUnmarshaller();
//
//			ByteArrayInputStream stream = new ByteArrayInputStream(source.getBytes());
//
//			products = (Collection<Product>) jaxbMarshaller.unmarshal(stream);
//
//
//		} catch (JAXBException e) {
//			e.printStackTrace();
//		}

		return products;
	}

	@Override
	public void notifyServerWithBuy(int client_id) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean checkItemInStore(Product product) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkAllItemsInStore(ShoppingCart cart) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addToCart(String product, int amount) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getId() {
		return id;
	}

	private static String getOutputAsAppXml(WebResource resource) {
		return resource.accept(MediaType.APPLICATION_XML).get(String.class);
	}

	private String demarshal() {
		return null;
	}
}
