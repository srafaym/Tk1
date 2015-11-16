package mvc;

import java.io.Serializable;
import java.rmi.Remote;
import java.util.HashMap;

public class Model implements Serializable, Remote{

	private static final long serialVersionUID = 1L;
	
	private HashMap<String, Integer> playersPoints = new HashMap<>();
	
	public void removePlayer(String playerName) {
		playersPoints.remove(playerName);
	}

	public void addPlayer(String playerName) {
		playersPoints.put(playerName, 0);
	}

	public void increasePoints(String playerName) {
		playersPoints.put(playerName, playersPoints.get(playerName) + 1);
	}

	public HashMap<String, Integer> getPoints() {
		return playersPoints;
	}
	
}
