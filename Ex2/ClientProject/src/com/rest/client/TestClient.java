package com.rest.client;


import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class TestClient {
	
	
	  static final String REST_URI = "http://localhost:8080/rest";
	    static final String ADD_PATH = "/list";
	    public static void main(String[] args)
	    {
	    	ClientConfig config = new DefaultClientConfig();
	    	
	    	
	    	Client client = Client.create(config);
	    	
	    	
	    	  WebResource service = client.resource(REST_URI);
	    	  WebResource addService = service.path(ADD_PATH);
	    	  
	    	  System.out.println(addService.queryParam(REST_URI,""));
	    	
	    	  System.out.println("Add Response: " + getResponse(addService));
	    	 
//	    	  GenericType<List<Product>> list = new GenericType<List<Product>>() {};
//	    	  
//	    	  List<Product> users = client.asyncViewResource(REST_URI+ADD_PATH).request(MediaType.APPLICATION_XML).get(list);
//	          if(users.isEmpty()){
//	        	  System.out.println("nothing");
//	          }
//	          System.out.println("Test case name: testGetAllUsers, Result: " + addService.get(Product.class));
	    	  
	    	  
	    	  
	    }
	    
	    
	 
	    
	    private static String getResponse(WebResource service) {
	        return service.accept(MediaType.APPLICATION_XML).get(ClientResponse.class).toString();
	    }
	    
	    

}
 