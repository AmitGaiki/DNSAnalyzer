/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import protocol.Activity;
import protocol.DBConnection;
import protocol.ProtocolStateMachine;
import protocol.StateMachine;

/**
 *
 * @author amit
 */
public class Main {
	
	public static void main(String[] args){
		ProtocolStateMachine psm = new ProtocolStateMachine(5,"h2","./db/","DNSPackets"); //4
		DBConnection dbConnect = new DBConnection("h2","./db/","DNSPackets");
		ProtocolStateMachine.setDbConnect(dbConnect);
		try {
			dbConnect.dropTable("Response");
			dbConnect.dropTable("Referral");
			dbConnect.dropTable("Query");
			
			dbConnect.createSchema(dbConnect.getDBName());
			dbConnect.createTable("CREATE TABLE IF NOT EXISTS "+dbConnect.getDBName()+".Query"+"(TRANSACTION_ID VARCHAR(255) PRIMARY KEY, "+"TIME_STAMP BIGINT, "+"PACKET OTHER)");
			dbConnect.createTable("CREATE TABLE IF NOT EXISTS "+dbConnect.getDBName()+".Response"+"(TRANSACTION_ID VARCHAR(255), "+"PACKET OTHER)");//, "+"FOREIGN KEY fk (TRANSACTION_ID) REFERENCES "+dbConnect.getDBName()+".Query(TRANSACTION_ID))");
			dbConnect.createTable("CREATE TABLE IF NOT EXISTS "+dbConnect.getDBName()+".Referral"+"(TRANSACTION_ID VARCHAR(255), "+"TIME_STAMP BIGINT)");
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
			while(ProtocolStateMachine.packetOffset < 31 ){
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
