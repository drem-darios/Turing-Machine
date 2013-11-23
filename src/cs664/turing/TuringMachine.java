package cs664.turing;

public class TuringMachine {

	public void main(String[] args) {
		
		// initialize tape
		Tape tape = new Tape();
		tape.init("");
		// initialize control
		Control control = new Control();
		control.loadProgram(new String[0]);
		// start simulator
		Character currSymbol = tape.read(); // should be start symbol
		// infinite loop while state is not halt
		control.getWriteSymbol(currSymbol);
		control.getNextDirection(currSymbol);
		control.performTransition(currSymbol);
	}
	
	
}
