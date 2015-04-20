/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import state.Activity;
import state.StateMachine;
import state.ProtocolStateMachine;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

/**
 *
 * @author amit
 */
public class Main {
	/**
	 * main method for the program
	 * @param args array of strings as command line arguments
	 */
	public static void main(String[] args){
		ProtocolStateMachine psm = new ProtocolStateMachine(5,"h2","./db/","DNSPackets"); //4
		try {
			psm.dbConnect.createSchema(psm.dbConnect.getDBName());
			//dbConnect.dropTable("Response");
			//dbConnect.dropTable("Referral");
			//dbConnect.dropTable("Query");
			psm.dbConnect.dropTable("Packets");
			
			psm.dbConnect.createTable("Packets","TIME_STAMP BIGINT, "+"TRANSACTION_ID VARCHAR(255), "+"TYPE_OF_PACKET VARCHAR(255), "+"TYPE_OF_QUERY SMALLINT, "+"AUTH_RESPONSE BOOLEAN, "+"RECURSION_DESIRED BOOLEAN, "+"RECURSION_AVAILABLE BOOLEAN, "+"RESPONSE_CODE SMALLINT, "+"QUESTION_COUNT SMALLINT, "+"RESPONSE_COUNT SMALLINT, "+"AUTHORITY_COUNT SMALLINT, "+"ADDITIONAL_COUNT SMALLINT, "+"QUESTION_NAME VARCHAR(255), "+"QUESTION_TYPE SMALLINT, "+"QUESTION_CLASS SMALLINT, "+"PAYLOAD BINARY(1000)");
			//dbConnect.createTable("Query","TRANSACTION_ID VARCHAR(255) PRIMARY KEY, "+"TIME_STAMP BIGINT, "+"PACKET OTHER");
			//dbConnect.createTable("Response","TRANSACTION_ID VARCHAR(255), "+"PACKET OTHER");//, "+"FOREIGN KEY fk (TRANSACTION_ID) REFERENCES "+dbConnect.getDBName()+".Query(TRANSACTION_ID))");
			//dbConnect.createTable("Referral","TRANSACTION_ID VARCHAR(255), "+"TIME_STAMP BIGINT");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Class<?> act;
		try {
			//act = Class.forName("Activity");
			psm.getStateMachine().getInitialState().onEntry = Activity.class.getMethod("EntryActionInit", null);
			psm.getStateMachine().getInitialState().nextState = Activity.class.getMethod("nextStateInit", StateMachine.class);
			psm.getStateMachine().getInitialState().onExit = Activity.class.getMethod("ExitActionInit", null);
			
			psm.getStateMachine().getState("A").onEntry = Activity.class.getMethod("EntryActionA", null);
			psm.getStateMachine().getState("A").nextState = Activity.class.getMethod("nextStateA", StateMachine.class);
			psm.getStateMachine().getState("A").onExit = Activity.class.getMethod("ExitActionA", null);
			
			psm.getStateMachine().getState("B").onEntry = Activity.class.getMethod("EntryActionB", null);
			psm.getStateMachine().getState("B").nextState = Activity.class.getMethod("nextStateB", StateMachine.class);
			psm.getStateMachine().getState("B").onExit = Activity.class.getMethod("ExitActionB", null);
			
			psm.getStateMachine().getState("C").onEntry = Activity.class.getMethod("EntryActionC", null);
			psm.getStateMachine().getState("C").nextState = Activity.class.getMethod("nextStateC", StateMachine.class);
			psm.getStateMachine().getState("C").onExit = Activity.class.getMethod("ExitActionC", null);
			
			psm.getStateMachine().getState("D").onEntry = Activity.class.getMethod("EntryActionD", null);
			psm.getStateMachine().getState("D").nextState = Activity.class.getMethod("nextStateD", StateMachine.class);
			psm.getStateMachine().getState("D").onExit = Activity.class.getMethod("ExitActionD", null);
			
			psm.getStateMachine().getState("E").onEntry = Activity.class.getMethod("EntryActionE", null);
			psm.getStateMachine().getState("E").nextState = Activity.class.getMethod("nextStateE", StateMachine.class);
			psm.getStateMachine().getState("E").onExit = Activity.class.getMethod("ExitActionE", null);
			
			psm.getStateMachine().getEndState().onEntry = Activity.class.getMethod("EntryActionEnd", null);
			psm.getStateMachine().getEndState().nextState = Activity.class.getMethod("nextStateEnd", StateMachine.class);
			psm.getStateMachine().getEndState().onExit = Activity.class.getMethod("ExitActionEnd", null);
		}  catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			//!(psm.stateMachine.getCurrentState().equals(psm.stateMachine.getEndState()))
			while(psm.packetOffset < 31 ){
				psm.getStateMachine().getCurrentState().nextState.invoke(new Activity(), psm.getStateMachine());
			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*for(String s : TransactionIDs){
			System.out.println(s);
		}*/
	
    	/*for(String s : Queries.keySet()){
    		System.out.print(s + ": ");
    		if(!responsePackets.get(s).isEmpty()){
    		for(Packet p : responsePackets.get(s)){
    			byte[] payload = PayloadRetriever.getUDPPayload(p);
    			System.out.print(DNSWrapper.getResponseCode(payload)+" ");
    		}
    	}
    		else
    			System.out.println("Empty");
     }*/
	
	}
	
}
