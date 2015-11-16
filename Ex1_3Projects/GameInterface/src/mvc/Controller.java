package mvc;


import java.rmi.RemoteException;
import java.util.HashMap;

import impl.IGameServer;

public class Controller {
	IGameServer server;

	Model model;
	HashMap<String, View> views = new HashMap<>();

	public Controller(IGameServer server, Model model) {
		this.server = server;
		this.model = model;
	}

	public void addViewDependency(String name, View view) throws RemoteException {
		this.views.put(name, view);
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
