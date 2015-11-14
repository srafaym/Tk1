package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import ServerInterface.IGameServer;
import ServerInterface.ServerContants;
import server.ServerImplementation;

public class GameClient {

	public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry rgs = LocateRegistry.getRegistry("localhost",ServerContants.RMI_PORT);
        IGameServer remote =  (IGameServer) rgs.lookup(ServerContants.RMI_ID);
        System.out.println(remote.isLoginValid("abc"));
        System.out.println(remote.isLoginValid("test"));

	}

}
