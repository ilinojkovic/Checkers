package rs.ac.uns.pmf.dmi.oop2.teamD.checkers.server;

import rs.ac.uns.pmf.dmi.oop2.teamD.checkers.user.IUser;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface for user database and a service for sending messages between users
 */
public interface IUserDb extends Remote {
	
	String RMI_NAME = "UserDb";

	/**
	 * Adds new user. Max number of users is 2.
	 * @param user
	 * @return Returns the ordinal number of a player. If number of players exceeded, returns -1.
	 * @throws RemoteException
	 */
	int add(IUser user) throws RemoteException;

	/**
	 * Removes user in case one decides to quit.
	 * Notifies the other player of this and ends game.
	 * @param user
	 * @throws RemoteException
	 */
	void remove(IUser user) throws RemoteException;

	/**
	 * Returns the opponent of the passed user
	 * @param user
	 * @return
	 * @throws RemoteException
	 */
	IUser getOpponent(IUser user) throws RemoteException;

	/**
	 * Sends message to other players
	 * @param sender
	 * @param message
	 * @throws RemoteException
	 */
	void send(IUser sender, String message) throws RemoteException;

}
