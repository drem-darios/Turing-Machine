package cs664.test;

import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;

import cs664.turing.TuringMachine;

public class TuringMachineTest {
	private TuringMachine tm = new TuringMachine();

	@Before
	public void setup() throws Exception {
		InputStream in = TuringMachineTest.class.getClassLoader()
				.getResourceAsStream("addition_program.tm"); // successor_program.tm
		tm.init(in);
	}

	@Test
	public void testSimulator() {
		tm.simulate();
	}

}
