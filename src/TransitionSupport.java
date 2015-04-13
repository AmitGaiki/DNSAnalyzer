import java.util.Arrays;


public class TransitionSupport {
		private State from,to;
		private Object caller;
		private Object[] arguments;
		private int exitparams,entryparams;
		
		/**
		 * used as setter to set to and from states.
		 * @param b the from state of transition
		 * @param e the to state of transition
		 */
		TransitionSupport(State b,State e){
			this.from = b;
			this.to = e;
			this.caller = new Activity();
			this.arguments = null;
		}
		
		/**
		 * used as setter for to and from states along with caller object and entry and exit parameters.
		 * @param b the from state of transition
		 * @param e the to state of transition
		 * @param callerObj caller object 
		 * @param exp exit parameter for the class
		 * @param enp entry parameter for the class
		 * @param parameters object parameters
		 */
		TransitionSupport(State b,State e,Object callerObj,int exp,int enp,Object...parameters){
			this.from = b;
			this.to = e;
			this.caller = callerObj;
			this.arguments = parameters;
			this.entryparams = enp;
			this.exitparams = exp;
		}
		
		/**
		 * sets entry parameter, exit parameter and other parameters for the class
		 * @param exp exit parameter for the class
		 * @param enp entry parameter for the class
		 * @param parameters object parameters
		 */
		public void setArguments(int exp,int enp,Object...parameters){
			this.arguments = parameters;
			this.entryparams = enp;
			this.exitparams = exp;
		}
		
		/**
		 * getter for from state
		 * @return from state for the transition
		 */
		public State getStartState(){
			return this.from;
		}
		
		/**
		 * getter for to state
		 * @return to state for transition
		 */
		public State getEndState(){
			return this.to;
		}
		
		/**
		 * getter for calling object
		 * @return calling object for transition
		 */
		public Object getCallingObject(){
			return this.caller;
		}
		
		/**
		 * getter for parameters and from argument
		 * @return array of arguments
		 */
		public Object[] getfromArguments(){
			return Arrays.copyOfRange(this.arguments,0,this.entryparams);
		}
		
		/**
		 * getter for parameters and to argument
		 * @return array of arguments
		 */
		public Object[] gettoArguments(){
			return Arrays.copyOfRange(this.arguments,this.entryparams,this.entryparams + this.exitparams);
		}
		
		/**
		 * getter for parameters only
		 * @return array of arguments
		 */
		public Object[] getArguments(){
			return this.arguments;
		}
}
