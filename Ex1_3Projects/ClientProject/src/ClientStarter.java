import impl.IGameServer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import mvc.Controller;
import mvc.Model;

public class ClientStarter {

	public static final String RMI_ID = "TestRMI";
	public static final int RMI_PORT = 2226;
	public static final String RMI_MODEL_ID = "Model";

	public static void main(String[] args) throws RemoteException,
			NotBoundException, InterruptedException {
		
		Registry rgs = LocateRegistry.getRegistry("localhost", RMI_PORT);
		IGameServer server = (IGameServer) rgs.lookup(RMI_ID);

		try {
			Model model = (Model) rgs.lookup(RMI_MODEL_ID);
			Controller controller = new Controller(server, model);
			GameClient clientG = new GameClient("Gosho", controller);
			server.login("Gosho", clientG);
			Thread.sleep(1000);
			GameClient clientP = new GameClient("Pesho", controller);
			server.login("Pesho", clientP);
			Thread.sleep(1000);
			GameClient clientT = new GameClient("Tosho", controller);
			server.login("Tosho", clientT);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
