package cs664.turing;

import java.util.HashMap;
import java.util.Map;

/**
 * Keeps track of different states.
 * 
 */
public class Control {

	/**
	 * Maps state id to the state it represents.
	 */
	private Map<Character, State> states = new HashMap<Character, State>();
	/**
	 * The current state the program is in.
	 */
	private State currState;

	public Control() {
	}

	/**
	 * Loads program states and transitions into control. This will overwrite
	 * any previously loaded programs and set the current state to state '0'.
	 * 
	 * @param program
	 *            - The program to load
	 */
	public void loadProgram(String[] program) {
		states.clear();
		for (String inst : program) {
			char[] instLine = inst.toCharArray();
			Character stateNum = instLine[0];
			State state;
			if (states.containsKey(stateNum)) {
				state = states.get(stateNum);
				state.addTransition(instLine[1], instLine[2], instLine[3],
						instLine[4]);
			} else {
				state = new State(instLine[1], instLine[2], instLine[3],
						instLine[4]);
			}

			states.put(stateNum, state);
		}

		currState = states.get('0');
	}

	/**
	 * Gets the symbol to write given the current symbol.
	 * 
	 * @param currSymbol
	 *            - The current symbol being pointed to by the read head.
	 * @return - The symbol to write to the tape.
	 */
	public Character getWriteSymbol(Character currSymbol) {
		return currState.getWrite(currSymbol);
	}

	/**
	 * Gets the next direction to move the read head in given the current
	 * symbol.
	 * 
	 * @param currSymbol
	 *            - The current symbol being pointed to by the read head.
	 * @return - The next direction to move the read head.
	 */
	public Character getNextDirection(Character currSymbol) {
		return currState.getDirection(currSymbol);
	}

	/**
	 * Moves the current state of the control to the next state given the
	 * current symbol.
	 * 
	 * @param currSymbol
	 *            - The current symbol being pointed to by the read head.
	 */
	public boolean performTransition(Character currSymbol) {
		Character nextState = currState.getNextState(currSymbol);
		currState = states.get(nextState);
		return currState != null;
	}
}
