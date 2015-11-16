import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerStarter {
	
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Server is started");
	}
}
