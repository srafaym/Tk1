package soapserverpackage;

import javax.xml.ws.Endpoint;

import java.io.Console;

import javax.jws.soap.*;

public class ServerPublisher {
	public static void main(String[] args) {
		   Endpoint.publish("http://localhost:5058/hello", new ProductImplementation() );
		   System.out.println("Server Started !!");
	}
}
