package mvc;

import java.awt.Point;
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

import impl.IGameClient;

public class Model implements Serializable, Remote{
	
	//TODO: Add fly position
	private static final long serialVersionUID = 1L;
	
	private HashMap<String, Integer> playersPoints = new HashMap<>();
	
	private int height = 380; // height of window - fly size
	private int width = 580; // width of window - fly size

	private Point point = new Point();
	
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
	
	public Point pickRandomPoint() {
		int x = (int) (Math.random() * width);
		int y = (int) (Math.random() * height);
		point.x = x;
		point.y = y;
		return point;
	}
	
	public Point getPoint() {
		return point;
	}

	
}
