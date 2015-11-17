package impl;

import java.awt.Point;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

public interface IGameClient extends Remote {
	void recieveFlyHunted(String playerName, int newPoints)
			throws RemoteException;

	void recieveFlyPosition(Point point) throws RemoteException;

	String getPlayerName() throws RemoteException;

	void receivePlayersPoints(HashMap<String, Integer> points)
			throws RemoteException;

	void removePlayer(String playerName) throws RemoteException;

	void addPlayer(String playerName) throws RemoteException;

}
