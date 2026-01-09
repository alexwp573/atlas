package atlas.chat;

import atlas.Engine;
import atlas.tool.*;
import atlas.tool.TextUserInterface.Listener;

public class Chat implements Listener{

	// --------------
	// PRIVATE FIELDS
	private String name = null; public String getName(){return this.name;}
	private Engine engine = null;
	private TextUserInterface tui = null;

	// --------------
	// PUBLIC METHODS
	public void execute(Command command){}

		// ------------
		// CONSTRUCTORS
		public Chat(Engine engine){this.engine = engine; this.tui = this.engine.getTui();}
}