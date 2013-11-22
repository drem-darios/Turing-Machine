package cs664.turing;

public class Tape {

	/**
	 * The current tape node.
	 */
	private TapeNode readHead;
	/**
	 * The start character of the program. Defaults to '$' if none is set.
	 */
	private char start = '$';
	/**
	 * The end character of the program. Defaults to '!' if none is set.
	 */
	private char end = '!';
	/**
	 * The blank character of the tape. Defaults to '#' if none is set.
	 */
	private char blank = '#';

	public Tape() {
		this(null, null, null);
	}

	/**
	 * Replaces the default start, end, and blank symbols of the tape. The
	 * defaults will be maintained if null is passed in.
	 * 
	 * @param start - The new start symbol to use or null if default.
	 * @param end - The new end symbol to use or null if default.
	 * @param blank - The blank start symbol to use or null if default.
	 */
	public Tape(Character start, Character end, Character blank) {
		if (start != null)
			this.start = start;
		if (end != null)
			this.end = end;
		if (blank != null)
			this.blank = blank;
		
		readHead = new TapeNode(this.start);
		readHead.next = new TapeNode(this.end);
		readHead.prev = new TapeNode(this.blank);
	}

	/**
	 * Moves the read head to the left.
	 * 
	 * @return - The current symbol the read head is pointing to after the move.
	 */
	public Character moveLeft() {
		if (readHead.prev == null) {
			readHead.prev = new TapeNode(blank);
		}

		readHead = readHead.prev;

		return readHead.symbol;
	}

	/**
	 * Moves the read head to the right.
	 * 
	 * @return - The current symbol the read head is pointing to after the move.
	 */
	public Character moveRight() {
		if (readHead.next == null) {
			readHead.next = new TapeNode(blank);
		}

		readHead = readHead.next;

		return readHead.symbol;
	}

	/**
	 * Writes a symbol to the current position of the read head.
	 * 
	 * @param symbol
	 *            - The symbol to write at read head.
	 */
	public void write(Character symbol) {
		readHead.symbol = symbol;
	}

	/**
	 * Reads the value of the current position of the read head.
	 * 
	 * @return - The symbol the read head is pointing to.
	 */
	public Character read() {
		return readHead.symbol;
	}

	/**
	 * A single node on the tape.
	 */
	private static class TapeNode {

		/**
		 * The current symbol this tape node represents.
		 */
		Character symbol;
		/**
		 * The node on the tape 'to the right' of this node.
		 */
		TapeNode next;
		/**
		 * The node 'to the left' of this node.
		 */
		TapeNode prev;

		/**
		 * A new tape node.
		 * 
		 * @param symbol - The symbol this node points to.
		 */
		TapeNode(Character symbol) {
			this.symbol = symbol;
		}

	}

}
