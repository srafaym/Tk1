package rest;


import java.io.IOException;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;

public class RestServerStarter {


	 public static final String URI = "http://localhost:8080/server/";
	 
	    public static void main(String[] args) {
	        try {
	           
	        	HttpServer server =  HttpServerFactory.create(URI);
	        	server.start();
	            System.out.println("Press Enter to stop the server. ");
	            System.in.read();
	            server.stop(0);
	        } catch (IllegalArgumentException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}
	    
	