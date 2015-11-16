
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import mvc.Model;
import impl.IGameClient;
import impl.IGameServer;

public class GameServer extends UnicastRemoteObject implements IGameServer {

	private static final long serialVersionUID = -2961914122660978332L;
	
	private int height = 380; // height of window - fly size
	private int width = 580; // width of window - fly size
	private boolean gameStarted;
	private Model model;
	private HashMap<String, IGameClient> clients = new HashMap<>();
	int lastX, lastY;

	public GameServer() throws RemoteException {
		super();
		model = new Model();
	}

	@Override
	public void login(String playerName, IGameClient client)
			throws RemoteException {

		model.addPlayer(playerName);
		clients.put(playerName, client);
		if (!gameStarted && clients.size() >= 2) {
			startGame();
			gameStarted = true;
		} else {
			client.recieveFlyPosition(lastX, lastY);
		}
		for(IGameClient c: clients.values()){
			c.receivePlayersPoints(model.getPoints());
		}
	}

	private void startGame() {
		int x = (int) (Math.random() * width);
		int y = (int) (Math.random() * height);
		lastX = x;
		lastY = y;
		System.out.println(x + " " + y);
		for (IGameClient client : clients.values()) {
			try {
				client.recieveFlyPosition(x, y);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void logout(String playerName) throws RemoteException {
		model.removePlayer(playerName);
		clients.remove(playerName);
		for(IGameClient c: clients.values()){
			c.removePlayer(playerName);
		}
	}

	@Override
	public void huntFly(String playerName) throws RemoteException {
		model.increasePoints(playerName);
		for (IGameClient client : clients.values()) {
			client.recieveFlyHunted(playerName,
					model.getPoints().get(playerName));
		}
		startGame();
	}
	
	public Model getModel(){
		return model;
	}
}
