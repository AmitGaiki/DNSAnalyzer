package protocol;

import java.util.HashMap;



public class ProtocolStateMachine {
	private StateMachine stateMachine;
	public static int packetOffset = -1;
	Transition transit;
	static Packet currentPacket;
	static HashMap<String,Long> Queries;
	static  Hasher responsePackets;
	final static String file = "data\\DNS_Traffic000.pcap";
	final static long timeout = 60000;   //1 minute = 60,000 millisecond
	static DBConnection dbConnect;
	
	public ProtocolStateMachine(int nost,String dbms,String location,String dbname){
		stateMachine = new StateMachine(nost);
		transit = new Transition();
		stateMachine.addObserver(transit);
		Queries = new HashMap<String,Long>();
		Queries.clear();
		responsePackets = new Hasher();
		responsePackets.clear();
//		dbConnect = new DBConnection(dbms,location,dbname);
//		dbConnect.getConnection();
	}

	public static boolean isTimeOut(long timer,long time){
		if(timer - time > timeout){
			return true;
		}
		return false;
	}
	
	public static void setCurrentPacket(Packet packet){
		currentPacket = packet;
	}

	public StateMachine getStateMachine() {
		return stateMachine;
	}

	public void setStateMachine(StateMachine stateMachine) {
		this.stateMachine = stateMachine;
	}

	public static DBConnection getDbConnect() {
		return dbConnect;
	}

	public static void setDbConnect(DBConnection dbConnect) {
		ProtocolStateMachine.dbConnect = dbConnect;
	}
	
	
	
	
}
