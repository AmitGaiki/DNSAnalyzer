package state;

import entity.DBConnection;
import entity.Hasher;
import pcap.Packet;
import java.util.HashMap;


/**
 * Contains fields for objects State Machine, Transition, Packet and Connection.
 * @author amit
 */
public class ProtocolStateMachine {
	private StateMachine stateMachine;
	public static int packetOffset = -1;
	Transition transit;
	static Packet currentPacket;
	static HashMap<String,Long> Queries;
	static  Hasher responsePackets;
	final static String file = "data/DNS_Traffic000.pcap";
	final static long timeout = 60000;   //1 minute = 60,000 millisecond
	public static DBConnection dbConnect;
	/**
	 * 
	 * @param nost the number of transition states State Machine should have.
	 * @param dbms database name in the format used in database url
	 * @param location location of database file to be stored.
	 * @param dbname name of the database that will be created
	 */
	public ProtocolStateMachine(int nost, DBConnection dbConnect){
		stateMachine = new StateMachine(nost);
		transit = new Transition();
		stateMachine.addObserver(transit);
		Queries = new HashMap<String,Long>();
		Queries.clear();
		responsePackets = new Hasher();
		responsePackets.clear();
		this.dbConnect = dbConnect;
	}

	/**
	 * checks whether the process has exceeded time limit
	 * @param timer
	 * @param time
	 * @return true if time exceeds timeout else false
	 */
	public static boolean isTimeOut(long timer,long time){
		if(timer - time > timeout){
			return true;
		}
		return false;
	}
	/**
	 * setter for Packet field
	 * @param instance of class Packet
	 */
	public static void setCurrentPacket(Packet packet){
		currentPacket = packet;
	}

	public StateMachine getStateMachine() {
		return stateMachine;
	}

	public void setStateMachine(StateMachine stateMachine) {
		this.stateMachine = stateMachine;
	}
	
	
}
