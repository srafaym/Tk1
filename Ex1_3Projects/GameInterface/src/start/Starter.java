package start;

import impl.IGameServer;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import server.GameServer;
import mvc.Controller;
import client.GameClient;

/**
 * This is the Main class that starts the server and two clients.
 *
 */
public class Starter {
	
	public static final String RMI_ID = "RMI_SERVER";
	public static final int RMI_PORT = 2227;


	public static void main(String[] args) throws InterruptedException,
			RemoteException, MalformedURLException, NotBoundException {

		GameServer server = new GameServer();
		Registry register = LocateRegistry.createRegistry(RMI_PORT);
		try {
			register.bind(RMI_ID, server);
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
		System.out.println("Server is started");
		
		Registry rgs = LocateRegistry.getRegistry("localhost", RMI_PORT);
		IGameServer serverRMI = (IGameServer) rgs.lookup(RMI_ID);

		try {
			Controller controller = new Controller(serverRMI);
			GameClient clientA = new GameClient("Alice", controller);
			server.login("Alice", clientA);
			Thread.sleep(1000);
			GameClient clientB = new GameClient("Bob", controller);
			server.login("Bob", clientB);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
