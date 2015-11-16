import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

import mvc.Controller;
import mvc.View;
import impl.IGameClient;

public class GameClient extends UnicastRemoteObject implements IGameClient {

	private static final long serialVersionUID = -8892336687416051981L;
	private View view;
	private String playerName;

	public GameClient(String playerName, Controller controller) throws RemoteException {
		super();
		this.playerName = playerName;
		view = new View(playerName, controller);
		controller.addViewDependency(playerName, view);
		view.createAndShowGUI();
	}

	@Override
	public void recieveFlyHunted(String playerName, int newPoints)
			throws RemoteException {
		view.changePoints(playerName, newPoints);
	}

	@Override
	public void recieveFlyPosition(int x, int y) throws RemoteException {
		view.addFlyAtPosition(x, y);
	}

	@Override
	public String getPlayerName() {
		return playerName;
	}

	@Override
	public void receivePlayersPoints(HashMap<String, Integer> points)
			throws RemoteException {
		view.setPoints(points);
	}

	@Override
	public void removePlayer(String playerName) {
		view.removePlayer(playerName);
	}

	@Override
	public void addPlayer(String playerName) throws RemoteException {
		view.addPlayer(playerName);		
	}

}
