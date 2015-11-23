package mvc;

import java.rmi.RemoteException;
import impl.IGameServer;

/**
 * This class is used from the game clients to pass messages to the server that
 * forwards them to the model.
 */
public class Controller {
	IGameServer server;

	public Controller(IGameServer server) {
		this.server = server;
	}

	public void notifyHit(String playerName) {
		try {
			server.huntFly(playerName);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void notifyQuit(String playerName) {
		try {
			server.logout(playerName);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
