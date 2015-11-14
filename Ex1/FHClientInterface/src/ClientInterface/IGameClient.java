package ClientInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IGameClient extends Remote {
	public static String playerName = "";
	public static String playerAddress = "";
	
	public static int playerPort = 0;
	public static int playerScore = 0;
	
	public void receiveFlyHunted(String playerName, int newPoints) throws RemoteException;
	public void receiveFlyPosition (String playerName, int newPoints) throws RemoteException;
	
	public void  IGameClient() throws RemoteException ;


}
