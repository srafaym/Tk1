package soap_server;

import java.io.Console;
import javax.*;
import javax.jws.soap.*;
import javax.xml.ws.Endpoint;

public class ServerPublisher {
	public static void main(String[] args) {
		   Endpoint.publish("http://localhost:8090/soap_server", new ServerImplementation() );
		   System.out.println("Server Started !!");
	}
}
