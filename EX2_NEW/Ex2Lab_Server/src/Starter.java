import java.io.IOException;

import javax.xml.ws.Endpoint;

import soap.ServerImplementation;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;

public class Starter {

	public static final String URI = "http://localhost:8080/server/";

	public static void main(String[] args) {

		Endpoint.publish("http://localhost:8090/soap",
				new ServerImplementation());
		System.out.println("SOAP Server Started.");

		try {
			HttpServer server = HttpServerFactory.create(URI);
			server.start();
			System.out.println("REST Server Started.");
			System.out.println("Press any key to stop server");
			System.in.read();
			server.stop(0);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
