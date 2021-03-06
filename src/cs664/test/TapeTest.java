package cs664.test;

import org.junit.Assert;
import org.junit.Test;

import cs664.turing.Tape;

public class TapeTest extends Assert {

	Tape tape = new Tape();

	/**
	 * Move around the tape a little just to make sure we can.
	 */
	@Test
	public void testMoveTape() {
		// One left
		Character symbol = tape.move('L');
		assertNotNull(symbol);
		assertEquals(symbol, tape.read());
		// Two right
		symbol = tape.move('L');
		assertNotNull(symbol);
		assertEquals(symbol, tape.read());

		symbol = tape.move('R');
		assertNotNull(symbol);
		assertEquals(symbol, tape.read());
		//Three left
		symbol = tape.move('L');
		assertNotNull(symbol);
		assertEquals(symbol, tape.read());

		symbol = tape.move('L');
		assertNotNull(symbol);
		assertEquals(symbol, tape.read());

		symbol = tape.move('L');
		assertNotNull(symbol);
		assertEquals(symbol, tape.read());
		// Four right
		symbol = tape.move('R');
		assertNotNull(symbol);
		assertEquals(symbol, tape.read());

		symbol = tape.move('R');
		assertNotNull(symbol);
		assertEquals(symbol, tape.read());
		
		symbol = tape.move('R');
		assertNotNull(symbol);
		assertEquals(symbol, tape.read());

		symbol = tape.move('R');
		assertNotNull(symbol);
		assertEquals(symbol, tape.read());
	}
	
	@Test
	public void testWriteSymbol() {
		Character before = tape.read();
		assertNotNull(before);
		// create anew char to replace read head symbol
		Character replace = 'b';
		tape.write(replace);
		// read current symbol back
		Character after = tape.read();
		assertNotNull(after);
		// verify symbol has changed.
		assertNotEquals(before, after);
		assertEquals(replace, after);
	}

	@Test
	public void testInitTape() {
		tape.init("111,111");
		assertEquals(tape.read(), new Character('$'));
	}
}
