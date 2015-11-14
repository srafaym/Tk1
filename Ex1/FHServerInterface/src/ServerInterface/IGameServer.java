package ServerInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import ClientInterface.IGameClient;

public interface IGameServer extends Remote{
    public boolean isLoginValid (String name) throws RemoteException;
    
    public void login (String playerName , IGameClient client) throws RemoteException;
    public void logout (String playerName ) throws RemoteException;
    public void huntFly (String playerName ) throws RemoteException;
    //public boolean clientStubCheck (IGameClient client) throws RemoteException;
    
}