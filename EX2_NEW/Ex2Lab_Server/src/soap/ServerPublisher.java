package soap;

import javax.xml.ws.Endpoint;

public class ServerPublisher {
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8090/soap",
				new ServerImplementation());
		System.out.println("Server Started !!");
	}
}
