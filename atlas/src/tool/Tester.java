package atlas.tool;

import atlas.Engine;
import atlas.module.TextUserInterface;
import atlas.tool.*;
import atlas.tool.tester.Debuger;

public class Tester implements Runnable, TextUserInterface.Listener{
	private String name = "Tester"; public String getName(){return this.name;}
	private static Logger logger = new Logger();
	private Engine engine = null;
	private TextUserInterface tui = null;

	public static void main(String[] args){}

	public Tester(Engine engine){this.engine = engine; this.tui = this.engine.getTui();}

	public void run(){
		this.tui.addInListener(this);
	}
	public void execute(Command command){}
}