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
import mvc.Model;
import client.GameClient;

public class Starter {
	
	public static final String RMI_ID = "TestRMI";
	public static final int RMI_PORT = 2226;
	public static final String RMI_MODEL_ID = "Model";


	public static void main(String[] args) throws InterruptedException,
			RemoteException, MalformedURLException, NotBoundException {

		GameServer server = new GameServer();
		Registry register = LocateRegistry.createRegistry(RMI_PORT);
		try {
			register.bind(RMI_ID, server);
			register.bind(RMI_MODEL_ID, server.getModel());
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
		System.out.println("Server is started");
		
		Registry rgs = LocateRegistry.getRegistry("localhost", RMI_PORT);
		IGameServer serverRMI = (IGameServer) rgs.lookup(RMI_ID);

		try {
			Model model = (Model) rgs.lookup(RMI_MODEL_ID);
			Controller controller = new Controller(serverRMI, model);
			GameClient clientG = new GameClient("Alice", controller);
			server.login("Gosho", clientG);
			Thread.sleep(1000);
			GameClient clientP = new GameClient("Bob", controller);
			server.login("Pesho", clientP);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
