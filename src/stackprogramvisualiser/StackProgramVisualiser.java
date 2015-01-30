package stackprogramvisualiser;

import stackprogramvisualiser.exceptions.CodeFormatException;
import stackprogramvisualiser.gui.Gui;

public class StackProgramVisualiser {

	private Gui gui;

	private ParsedCode parsedCode;

	public StackProgramVisualiser() {
	}

	// let's get the party started
	public void init() {
		gui = new Gui(this);
		gui.build();

		// default RHS view
		gui.setProgramCounter(null);
		gui.redrawStackGui();
	}

	/* button action listeners */

	public void onRunProgram() {
		// update gui
		gui.startRunMode();
		gui.setEditorLock(true);

		// try to parse the program
		if (!parseProgram()) return;
	}

	public void onStop() {
		// update gui
		gui.stopRunMode();
		gui.setEditorLock(false);
		gui.setProgramCounter(null);
		gui.setStackDataSource(null);
		gui.redrawStackGui();
	}

	public void onStartStepMode() {
		// update gui
		gui.startStepMode();
		gui.setEditorLock(true);
	}

	public void onQuitStepMode() {
		// update gui
		gui.stopStepMode();
		gui.setEditorLock(false);
		gui.setProgramCounter(null);
		gui.setStackDataSource(null);
		gui.redrawStackGui();
	}

	public void onNextStep() {

	}

	/* program execution methods */

	private boolean parseProgram() {
		// get code
		String program = gui.getEditorContents().trim();

		// empty?
		if (program.length() == 0) {
			gui.outputTerminalError("No program to run");
			return false;
		}

		// parse
		Parser parser = new Parser(program);
		try {
			parsedCode = parser.parse();
			return true;
		} catch (CodeFormatException e) {
			gui.outputTerminalError("CodeFormatException on line " + e.getLineNumber());
			if (e.getIie() != null) {
				gui.outputTerminalError("    - Invalid instruction name '" + e.getIie().getInstructionValue() + "'");
			}
			return false;
		}
	}

	/* main method to start things off */

	public static void main(String[] args) {
		new StackProgramVisualiser().init();
	}

}
