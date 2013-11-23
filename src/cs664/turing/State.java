package cs664.turing;

import java.util.HashMap;
import java.util.Map;

public class State {

	/**
	 * Map of read symbol to write symbol
	 */
	private Map<Character, Character> writeVals = new HashMap<Character, Character>();
	/**
	 * Map of read symbol to direction
	 */
	private Map<Character, Character> dirVals = new HashMap<Character, Character>();
	/**
	 * Map of read symbol to transition state
	 */
	private Map<Character, Character> stateVals = new HashMap<Character, Character>();

	public State(Character readChar, Character writeChar, Character direction,
			Character nextState) {
		addTransition(readChar, writeChar, direction, nextState);
	}

	/**
	 * Adds a new transition rule to this state.
	 * 
	 * @param readChar
	 *            - The symbol read in from the tape.
	 * @param writeChar
	 *            - The symbol to write to the tape.
	 * @param direction
	 *            - The direction to move the read head of the tape.
	 * @param nextState
	 *            - The id of the next state to transition to.
	 */
	public void addTransition(Character readChar, Character writeChar,
			Character direction, Character nextState) {
		writeVals.put(readChar, writeChar);
		dirVals.put(readChar, direction);
		stateVals.put(readChar, nextState);
	}

	/**
	 * Gets the symbol to write given the symbol read.
	 * 
	 * @param readChar
	 *            - The symbol pointed to by the read head.
	 * @return - The symbol to write at the current read head position.
	 */
	public Character getWrite(Character readChar) {
		return writeVals.get(readChar);
	}

	/**
	 * Gets the direction to move the read head in given the symbol read.
	 * 
	 * @param readChar
	 *            - The symbol pointed to by the read head.
	 * @return - The direction to move the read head.
	 */
	public Character getDirection(Character readChar) {
		return dirVals.get(readChar);
	}

	/**
	 * The next state to transition to given the symbol read.
	 * 
	 * @param readChar
	 *            - The symbol pointed to by the read head.
	 * @return - The state to transition to.
	 */
	public Character getNextState(Character readChar) {
		return stateVals.get(readChar);
	}

}