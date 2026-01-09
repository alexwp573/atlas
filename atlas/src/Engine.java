package atlas;

import atlas.tool.*;
import atlas.tool.TextUserInterface.Listener;
import atlas.game.*;

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
	private GameLauncher gameLauncher = new GameLauncher(this);
		private Thread gameLauncherThread = null;
			private void enterGameLauncher(){this.gameLauncherThread = new Thread(this.gameLauncher); this.gameLauncherThread.start();}
	private Connection connection = new Connection(this);
		private Thread connectionThread = null;
			private void enterConnection(){this.connectionThread = new Thread(this.connection); this.connectionThread.start();}

	// --- BB-CODE ---
	// PRIVATE METHODS
	private void printHelp(){
		Debuger.pl("test / t \n\t- Enters test module");
		Debuger.pl("conn / t \n\t- Enters connection module");
		Debuger.pl("gl / t \n\t- Enters games launcher module");
	}

	// ----- CODE ---
	// PUBLIC METHODS
	public void execute(Command command){
		switch(command.getCommand()){
			case "help": case "h": this.printHelp(); break;
			case "test": this.enterTester(); break;
			case "gl": this.enterGameLauncher(); break;
			case "conn": this.enterConnection(); break;
			default: Debuger.pl("Unknown command...");
		}
	}

}