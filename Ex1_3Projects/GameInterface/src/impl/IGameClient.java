package impl;

import java.awt.Dimension;
import java.awt.Point;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

/**
 * This interface declares the required logic from a game client.
 */
public interface IGameClient extends Remote {
	
	void receiveFlyPosition(Point point) throws RemoteException;

	void receivePlayersPoints(HashMap<String, Integer> points)
			throws RemoteException;

	void receiveWindowSize(Dimension windowSize) throws RemoteException;
}
