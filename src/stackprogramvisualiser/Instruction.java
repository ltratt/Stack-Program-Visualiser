package stackprogramvisualiser;

import stackprogramvisualiser.exceptions.InvalidInstructionException;

public class Instruction {

	private Command command;
	private Integer intArg = null;
	private String strArg = null;

	public Instruction(String command) throws InvalidInstructionException {
		setCommand(command);
	}

	public Instruction(String command, int intArg) throws InvalidInstructionException {
		setCommand(command);
		this.intArg = intArg;
	}

	public Instruction(String command, String strArg) throws InvalidInstructionException {
		setCommand(command);
		this.strArg = strArg;
	}

	private void setCommand(String command) throws InvalidInstructionException {
		try {
			this.command = Command.valueOf(command);
		} catch (IllegalArgumentException iae) {
			throw new InvalidInstructionException(command);
		}
	}

	public void execute() {
		StackProgramVisualiser.dataStack.push(0);
		StackProgramVisualiser.programCounter += 1;
	}

	public enum Command {
		// basic inputs
		INT,

		// arithmetic
		ADD,
		SUB,

		// output
		PRINT
	}

}
