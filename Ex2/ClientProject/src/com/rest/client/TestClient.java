package com.rest.client;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class TestClient {

	static final String REST_URI = "http://localhost:8080/server";
	static final String ADD_PATH = "rest/list";
	static final String ADD_TO_CART ="rest/add";
	
	
	public static void main(String[] args) {
		ClientConfig config = new DefaultClientConfig();

		Client client = Client.create(config);

		WebResource service = client.resource(REST_URI);

		WebResource orderService = service.path(ADD_PATH);
		System.out.println("orderService Response: " + getOutputAsAppXml(orderService));
//		System.out.println("Sub Output as XML: " + getOutputAsAppXml(orderService));

//		System.out.println("orderService Response: " + getResponseJson(orderService));
//		System.out.println("Sub Output as XML: " + getOutputAsAppJson(orderService));
		
		
		
		 WebResource addService = service.path(ADD_TO_CART).path(7 + "/"+"Book"+"/"+7.5+"/"+15+"/"+50+"/"+6);
		 System.out.println("Add Output as XML: " + getOutputAsAppXml(addService));
		
      


	}
	
//	    private static String getOutputAsXML(WebResource service) {
//	        return service.accept(MediaType.TEXT_XML).get(String.class);
//	    }

//	private static String getResponse(WebResource service) {
//		return service.accept(MediaType.APPLICATION_XML).get(ClientResponse.class).toString();
//	}
//
	private static String getOutputAsAppXml(WebResource service) {
		return service.accept(MediaType.APPLICATION_XML).get(String.class);
	}

//	private static String getResponseJson(WebResource service) {
//		return service.accept(MediaType.APPLICATION_XML).get(ClientResponse.class).toString();
//	}

//	private static String getOutputAsAppJson(WebResource service) {
//		return service.accept(MediaType.APPLICATION_JSON).get(String.class);
//	}

}
