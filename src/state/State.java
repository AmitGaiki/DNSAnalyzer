package state;

import java.lang.reflect.Method;

/**
 * This class describes the entry, exit and next state of each transition state during the course of program
 * @author amit
 */
public class State {
	private String stateName;
	
	public Method onEntry;
	public Method onExit;
	public Method nextState;
	
	/**
	 * getter for name of the state
	 * @return name of state
	 */
	public String getName(){
		return this.stateName;
	}
	
	/**
	 * setter for name of the state
	 * @param name name of the state
	 */
	public void setName(String name){
		this.stateName = name;
	}
	
	/**
	 * constructor that takes name of state as parameter
	 * @param name name of the state
	 */
	State(String name){
		this.stateName = name;
	}
	
	/**
	 * setter for entry method for this state
	 * @param entry entry method for this state
	 */
	public void addEntryAction(Method entry){
		this.onEntry = entry;
	}
	
	/**
	 * setter for exit method for this state
	 * @param exit exit method for this state
	 */
	public void addExitAction(Method exit){
		this.onExit = exit;
	}
	
	/**
	 * setter for transition method for this state
	 * @param transitions next method for the current state
	 */
	public void addTransitions(Method transitions){
		this.nextState = transitions;
	}
}
