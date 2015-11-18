package mvc;

import java.awt.Dimension;
import java.awt.Point;
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import impl.IGameClient;

/**
 * This class represents the model of the game - logged clients, window size and
 * fly position, scores.
 */
public class Model implements Serializable, Remote {

	private static final long serialVersionUID = 1L;

	private HashMap<String, Integer> playersPoints = new HashMap<>();
	private HashMap<String, IGameClient> clients = new HashMap<>();

	private boolean gameStarted;

	private static final Dimension windowSize = new Dimension(800, 600);

	// Fly image has width 53 and height 67
	private static final int width = 747; /* width of window - fly size */
	private static final int height = 500; /*
											 * height of window - fly height -
											 * panel above
											 */

	private Point point = new Point();

	public void removePlayer(String playerName) throws RemoteException {
		playersPoints.remove(playerName);
		clients.remove(playerName);
		if (clients.size() > 0) {
			sendChangedPointsToClients();
		}
	}

	public void addPlayer(String playerName, IGameClient client)
			throws RemoteException {
		clients.put(playerName, client);
		playersPoints.put(playerName, 0);
		if (!gameStarted) {
			sendClientsWindowSize();
			startNewGame();
			gameStarted = true;
		} else {
			sendClientWindowSize(client);
			sendClientFlyPosition(client);
		}
		sendChangedPointsToClients();
	}

	private void sendChangedPointsToClients() throws RemoteException {
		for (IGameClient c : clients.values()) {
			c.receivePlayersPoints(playersPoints);
		}
	}

	public void increasePoints(String playerName) throws RemoteException {
		playersPoints.put(playerName, playersPoints.get(playerName) + 1);
		sendChangedPointsToClients();
	}

	public void pickRandomPoint() {
		int x = (int) (Math.random() * width);
		int y = (int) (Math.random() * height);
		point.x = x;
		point.y = y;
	}

	public void sendClientsFlyPosition() {
		for (IGameClient client : clients.values()) {
			sendClientFlyPosition(client);
		}
	}

	private void sendClientFlyPosition(IGameClient client) {
		try {
			client.receiveFlyPosition(point);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void startNewGame() {
		pickRandomPoint();
		sendClientsFlyPosition();
	}

	public void sendClientsWindowSize() {
		for (IGameClient client : clients.values()) {
			sendClientWindowSize(client);
		}
	}

	private void sendClientWindowSize(IGameClient client) {
		try {
			client.receiveWindowSize(windowSize);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
