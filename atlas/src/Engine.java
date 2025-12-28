package atlas;

import atlas.tool.*;
import atlas.module.TextUserInterface;

public class Engine implements Runnable, TextUserInterface.Listener{

	// ----------------------------------------- BB-CODE ---
	// TEXT USER INTERFACE PRIVATE & PUBLIC FIELDS & METHODS
	private String name = "Engine"; public String getName(){return this.name;}
	private TextUserInterface tui = new TextUserInterface(); public TextUserInterface getTui(){return this.tui;}
		private Thread tuiThread = new Thread(tui);
			public void run(){this.tui.addInListener(this); this.tuiThread.start();}
	private Tester tester = new Tester(this);
		private Thread testerThread = null;
			private void enterTester(){this.testerThread = new Thread(this.tester); this.testerThread.start();}
	private Connection connection = new Connection(this);
		private Thread connectionThread = null;
			private void enterConnection(){this.connectionThread = new Thread(this.connection); this.connectionThread.start();}

	// --- BB-CODE ---
	// PRIVATE METHODS
	private void printHelp(){
		Debuger.pl("Command formula:\n\t >>> command [:argument] [!modificator] [-option [value]] [-option [value]] ...");
		Debuger.pl("test / t \n\t- Enters test module");
	}

	// ----- CODE ---
	// PUBLIC METHODS
	public void execute(Command command){
		switch(command.getCommand()){
			case "help": case "h": this.printHelp(); break;
			case "test": this.enterTester(); break;
			case "conn": this.enterConnection(); break;
			default: Debuger.pl("Unknown command...");
		}
	}

}