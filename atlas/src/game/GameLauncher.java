package atlas.game;

import atlas.Engine;

import atlas.tool.Command;
import atlas.tool.Debuger;
import atlas.tool.TextUserInterface;
import atlas.tool.TextUserInterface.Listener;

import atlas.game.*;

public class GameLauncher implements Listener, Runnable{
	
	private static void printHelp(){
		Debuger.pl("games\n\tlists all avaible games");
		Debuger.pl("mysteryBox\n\tenters Mystery Box game");
	}
	
	private Engine engine = null;
	
	public GameLauncher(Engine engine){this.engine = engine; this.tui = this.engine.getTui();}
	public Game game = null;
		public Thread gameThread = null;
	
	// ----------
	// OVERWRITES
	private TextUserInterface tui = null; public TextUserInterface getTui(){return this.tui;}
	public void run(){
		this.tui.addInListener(this);
	}
	public String getName(){return "GameLauncher";}
	public void execute(Command cmnd){
		switch(cmnd.getCommand()){
			case "h": case "help": GameLauncher.printHelp(); break;
			case "mysteryBox":
				//this.game = new MysteryBox(this);
				this.gameThread = new Thread(this.game);
				this.gameThread.start();
				break;
			case "games":
				Debuger.pl("Mystery Box (mysterBox)");
				break;
		}
	}
}