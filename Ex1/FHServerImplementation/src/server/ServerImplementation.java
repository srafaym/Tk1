package server;

import ClientInterface.IGameClient;
import ServerInterface.IGameServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerImplementation extends UnicastRemoteObject implements IGameServer {

	protected ServerImplementation() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isLoginValid(String name) throws RemoteException {
		if (name.equals("test")){
	        return true;
	        }
	        return false;
	}

	@Override
	public void login(String playerName, IGameClient client) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logout(String playerName) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void huntFly(String playerName) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
