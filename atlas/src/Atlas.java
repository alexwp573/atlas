package atlas;

import atlas.tool.*;
import atlas.module.TextUserInterface;

public class Atlas{
	private Engine engine = new Engine();
	private Thread engineThread = new Thread(this.engine);
	public void startEngine(){this.engineThread.start();}
	public static void main(String[] args){
		new Atlas().startEngine();
	}
}