import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import soap.Product;
import soap.Products;
import soap.ShoppingCart;

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
	static final String GET_PRODUCTS_PATH = "rest/products";
	static final String BUY_PATH = "rest/buy";
	static final String ADD_TO_CART_PATH = "rest/add";
	static final String CHECK_PATH = "rest/check";
	com.sun.jersey.api.client.Client client;

	WebResource service;

	private int id;

	public RestClient(int id) {
		ClientConfig config = new DefaultClientConfig();

		client = com.sun.jersey.api.client.Client.create(config);
		this.service = client.resource(REST_URI);
		this.id = id;
		this.products = getProductsFromServer();
		view = new View("Rest", products, this);
		view.createAndShowGUI();
	}

	@Override
	public LinkedList<Product> getProductsFromServer() {
		WebResource orderService = service.path(GET_PRODUCTS_PATH);
		String marshalled = getOutputAsAppXml(orderService);
		System.out.println("orderService Response: "
				+ orderService.getURI().toString() + " : " + marshalled);

		return (LinkedList<Product>)unmarshal(marshalled).getProducts();
	}

	public Products unmarshal(String source) {
		// TODO
		Products products = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Products.class);
			Unmarshaller jaxbMarshaller = jaxbContext.createUnmarshaller();
			ByteArrayInputStream stream = new ByteArrayInputStream(
					source.getBytes());
			products = (Products) jaxbMarshaller.unmarshal(stream);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return products;
	}

	@Override
	public void notifyServerWithBuy(int clientId) {
		WebResource checkService = client.resource(REST_URI + "/"
				+ BUY_PATH + "?clientId=" + getId());
		ClientResponse response = checkService.type(MediaType.APPLICATION_XML)
				.post(ClientResponse.class);
		String result = response.getEntity(String.class);
		System.out.println(result);
	}

	@Override
	public boolean checkItemInStore(Product product) {
		WebResource checkService = service.path(CHECK_PATH);
		ClientResponse response = checkService.type(MediaType.APPLICATION_XML)
				.post(ClientResponse.class, marshal(product)); // TODO: or
																// without
																// marshal?
		String result = response.getEntity(String.class);
		System.out.println(result);
		return Boolean.parseBoolean(result);
	}

	@Override
	public void addToCart(String product, int amount) {
		WebResource checkService = client.resource(REST_URI + "/"
				+ ADD_TO_CART_PATH + "?clientId=" + getId());
		System.out.println(checkService.toString());
		
		
		Product purchase = new Product();
		purchase.setName(product);
		purchase.setItemCount(amount);
		ClientResponse response = checkService.type(MediaType.APPLICATION_XML)
				.post(ClientResponse.class, marshal(purchase)); // TODO: or
																// without
																// marshal?
		String result = response.getEntity(String.class);
		System.out.println(result);
	}

	@Override
	public int getId() {
		return id;
	}

	private static String getOutputAsAppXml(WebResource resource) {
		return resource.accept(MediaType.APPLICATION_XML).get(String.class);
	}

	private String marshal(Product product) {
		StringBuilder sb = new StringBuilder();
		try {
			// File file = new File("C:\\file.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Product.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(
					javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true);

			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			jaxbMarshaller.marshal(product, stream);
			try {
				stream.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			sb.append(stream);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

		System.out.println(sb.toString());
		return sb.toString();
	}

}
