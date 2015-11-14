package server;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Logger;

import ServerInterface.IGameServer;
import ServerInterface.ServerContants;
public class Server {

	public static void main(String[] args) throws RemoteException{
	    ServerImplementation serverImp = new ServerImplementation();
	    Registry register = LocateRegistry.createRegistry(ServerContants.RMI_PORT);
	    try {
			register.bind(ServerContants.RMI_ID, serverImp);
		} catch (AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.err.println("Server is started");
	}

}
