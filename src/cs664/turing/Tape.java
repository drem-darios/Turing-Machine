package cs664.turing;

import java.io.IOException;
import java.io.OutputStream;

public class Tape {

	public static final Character LEFT = 'L';
	public static final Character RIGHT = 'R';
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
	 * @param start
	 *            - The new start symbol to use or null if default.
	 * @param end
	 *            - The new end symbol to use or null if default.
	 * @param blank
	 *            - The blank start symbol to use or null if default.
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
	 * Initializes tape with content string left to right starting. This will
	 * fill everything between the start and end symbols. If the tape contained
	 * any data it will be overwritten.
	 * 
	 * @param contents
	 *            - The contents to write to the tape
	 */
	public void init(String contents) {
		readHead = new TapeNode(start);
		for (char c : contents.toCharArray()) {
			TapeNode temp  = new TapeNode(c);
			readHead.next = temp;
			temp.prev = readHead;
			readHead = readHead.next;
		}
		readHead.next = new TapeNode(end);
		reset();
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
	
	public void print(OutputStream stream) throws IOException {
		this.reset();
		StringBuilder sb = new StringBuilder("Tape contents: ");
		while(!readHead.symbol.equals(end)) {
			sb.append(readHead.symbol);
			sb.append(" ");
			readHead = readHead.next;
		}
		sb.append(readHead.symbol);
		sb.append('\n');
		stream.write(sb.toString().getBytes());
		this.reset();
	}
	
	/**
	 * Moves the read head of the tape in the direction given.
	 */
	public Character move(Character direction) {
		if(direction.equals(LEFT)) {
			return moveLeft();
		}
		else if(direction.equals(RIGHT)) {
			return moveRight();
		}
		else {
			throw new IllegalStateException();
		}
	}
	
	/**
	 * Moves the read head to the left.
	 * 
	 * @return - The current symbol the read head is pointing to after the move.
	 */
	private Character moveLeft() {
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
	private Character moveRight() {
		if (readHead.next == null) {
			readHead.next = new TapeNode(blank);
		}

		readHead = readHead.next;

		return readHead.symbol;
	}
	
	/**
	 * Resets the read head to the start of the program.
	 */
	private void reset() {
		while (readHead.symbol != start ) {
			if(readHead.prev.symbol != blank) {
				readHead = readHead.prev;				
			}
			else
			{
				readHead = readHead.next;
			}
		}
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
		 * @param symbol
		 *            - The symbol this node points to.
		 */
		TapeNode(Character symbol) {
			this.symbol = symbol;
		}

	}

}
