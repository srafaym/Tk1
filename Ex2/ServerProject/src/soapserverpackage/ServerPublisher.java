package soapserverpackage;

import javax.xml.ws.Endpoint;



public class ServerPublisher {
	public static void main(String[] args) {
		   Endpoint.publish("http://localhost:5058/hello", new ProductImplementation() );
	}
}
