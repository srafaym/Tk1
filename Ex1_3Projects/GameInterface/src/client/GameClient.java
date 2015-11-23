package client;

import java.awt.Dimension;
import java.awt.Point;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

import mvc.Controller;
import mvc.View;
import impl.IGameClient;

/**
 * This class implements the game client logic.
 */
public class GameClient extends UnicastRemoteObject implements IGameClient {

	private static final long serialVersionUID = -8892336687416051981L;
	private View view;

	public GameClient(String playerName, Controller controller)
			throws RemoteException {
		super();
		view = new View(playerName, controller);
	}

	@Override
	public void receiveFlyPosition(Point point) throws RemoteException {
		view.addFlyAtPosition(point);
	}

	@Override
	public void receivePlayersPoints(HashMap<String, Integer> points)
			throws RemoteException {
		view.setPoints(points);
	}

	@Override
	public void receiveWindowSize(Dimension windowSize) {
		view.receiveWindowSize(windowSize);
		view.createAndShowGUI();
	}
}
