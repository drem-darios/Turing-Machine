To compile the java code from the command line, run the following command:

javac -d bin -sourcepath src src/cs664/turing/TuringMachine.java

This will put all the compiled code into a bin directory in the project directory. 
You may want to copy the test files(under src/) over to the bin as well.
From inside the bin directory you can run:
java cs664.turing.TuringMachine addition_program.tm

You can also pass in the program as command line arguments though it is much
easier to just pass in the file.

addition_program.tm
$111,11!
0$$R1
111R1
1,1R2
211R2
2!!L3
31!L4
411L4
4$$R5

-Uses $ as start ! as end and # as blanks. 
-Values are separated by commas.
-Program ends when a state is reached that has no other states to go to.

*Bonus*
If you are using Eclipse or some other IDE you can run unit tests that test each
individual piece of the Turing Machine. 