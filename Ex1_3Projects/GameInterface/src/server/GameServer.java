package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import mvc.Model;
import impl.IGameClient;
import impl.IGameServer;

/**
 * This class implements the game server logic.
 */
public class GameServer extends UnicastRemoteObject implements IGameServer {

	private static final long serialVersionUID = -2961914122660978332L;

	private Model model;

	public GameServer() throws RemoteException {
		super();
		model = new Model();
	}

	@Override
	public void login(String playerName, IGameClient client)
			throws RemoteException {
		model.addPlayer(playerName, client);
	}

	@Override
	public void logout(String playerName) throws RemoteException {
		model.removePlayer(playerName);
	}

	@Override
	public void huntFly(String playerName) throws RemoteException {
		model.increasePoints(playerName);
		model.startNewGame();
	}

}
