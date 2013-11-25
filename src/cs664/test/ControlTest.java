package cs664.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cs664.turing.Control;

public class ControlTest extends Assert {

	Control control = new Control();
	// Program to add one to a number separated by a zero I think...
	String[] program = { "0$$R1", "100R1", "111R1", "1!!L2", "210L2", "201L3",
			"311L3", "3$$R4" };
	// adds two numbers together separated by a comma
	String[] program2 = { "$111,11!", "0$$R1", "111R1", "1,1R2", "211R2",
			"2!!L3", "31!L4", "411L4", "4$$R5" };

	@Before
	public void testLoadProgram() {
		control.loadProgram(program2);
	}

	@Test
	public void testPerformTransition() {
		control.performTransition('$');
	}

	@Test
	public void testNextDirection() {
		Character nextDir = control.getNextDirection('$');
		assertTrue(nextDir.equals('R'));
	}

	@Test
	public void testGetWriteSymbol() {
		Character write = control.getWriteSymbol('$');
		assertTrue(write.equals('$'));
	}

}
