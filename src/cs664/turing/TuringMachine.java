package cs664.turing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TuringMachine {

	private static final Logger log = Logger.getLogger(TuringMachine.class
			.getName());
	private Tape tape = new Tape();
	private Control control = new Control();

	public static void main(String[] args) {
		TuringMachine tm = new TuringMachine();
		tm.init(args);
		tm.simulate();
	}

	/**
	 * Allow loading contents from an input stream. This gives the ability to
	 * load from a file.
	 */
	public void init(InputStream in) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		List<String> programInst = new ArrayList<String>();
		String tapeInput = reader.readLine();
		tape.init(tapeInput);
		String line;
		while ((line = reader.readLine()) != null) {
			programInst.add(line);
		}
		reader.close();
		control.loadProgram(programInst.toArray(new String[programInst.size()]));

	}

	/**
	 * Allow loading contents from an array of arguments. This gives the ability
	 * to load from the command line by passing in program arguments.
	 */
	public void init(String[] args) {
		// initialize tape
		tape.init(args[0]);
		String[] programInst = new String[args.length - 1];
		for (int i = 1; i < args.length; i++) {
			programInst[i - 1] = args[i];
		}
		// initialize control
		control.loadProgram(programInst);
	}

	/**
	 * Starts the Turing Machine simulation
	 */
	public void simulate() {
		boolean run = true;
		try {
			tape.print(System.out);
			// infinite loop while state is not halt
			while (run) {
				Character currSymbol = tape.read(); // should be start symbol
				Character write = control.getWriteSymbol(currSymbol);
				Character dir = control.getNextDirection(currSymbol);
				tape.write(write);
				tape.move(dir);
				run = control.performTransition(currSymbol);
				currSymbol = tape.read();
			}
			tape.print(System.out);
		} catch (Exception e) {
			log.log(Level.SEVERE, "Something bad happened during simulation.", e);
		}
	}

}
