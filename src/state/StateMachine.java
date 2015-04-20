package state;


import state.State;
import java.util.Observable;

/**
 * Class that stores initial state, current state, end state, all transition states and total number of possible states
 * @author amit
 */
public class StateMachine extends Observable  {

	private State currentState;
	private State initialState;
	private State endState;
	private State[] states;
	private int numofStates;
	
	/**
	 * constructor to initialize initial, transition and end states for the class. transition states are named sequentially starting from 'A', 'B', ... upto the total number of states.
	 * @param nost total number of transition states
	 */
	StateMachine(int nost){
		char name = 'A';
		this.initialState = new State("Initial");
		this.endState = new State("End");
		this.numofStates = nost;
		this.states = new State[this.numofStates];
		for(int i = 0;i < this.numofStates;i++){
			this.states[i] = new State(Character.toString((char)(name + i)));
		}
		this.currentState = this.initialState;
	}
	
	/**
	 * setter for current state. This method also notifies observers about the change in state.
	 * @param t 
	 */
	public void setCurrentState(TransitionSupport t){
		this.currentState = t.getEndState();
		setChanged();
		notifyObservers(t);
	}
	
	/**
	 * getter for initial state.
	 * @return initial state of this state machine
	 */
	public State getInitialState(){
		return this.initialState;
	}
	
	/**
	 * getter for end state.
	 * @return end state for this state machine
	 */
	public State getEndState(){
		return this.endState;
	}
	
	/**
	 * getter for total number of states for this state machine
	 * @return total number of states
	 */
	public int getNumberofStates(){
		return this.numofStates;
	}
	
	/**
	 * getter for a particular state according to state name
	 * @param name name of the state to get
	 * @return state as per name in parameter
	 */
	public State getState(String name){
		for(State st : this.states){
			if(st.getName().equalsIgnoreCase(name))
				return st;
		}
		System.out.println("State not found!");
		return null;
	}
	
	/**
	 * getter for current state
	 * @return current state of the state machine.
	 */
	public State getCurrentState(){
		return this.currentState;
	}
	
}
	
