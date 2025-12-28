package atlas.module;

import atlas.tool.*;
import atlas.tool.command.InputGetter;

public class TextUserInterface implements Runnable{

	public interface Listener{
		public void execute(Command command);
		public String getName();
	}

	// --------------
	// PRIVATE FIELDS
	private boolean running = true;
	private DataStruct.Table<Listener> inputListeners = new DataStruct().new Table<>();// stores objects that implements CommandListener
		public void addInListener(Listener listener){this.inputListeners.add(listener);}
		public boolean remListener(Listener listener){if(listener == this.inputListeners.checkLast()){this.inputListeners.take(); return true;} return false;}
	private Command command = null;
		private String whoIsListening = null;
			public String whoIsListening(){
				this.whoIsListening = "ATLAS";
				for(int i = 0; i < this.inputListeners.size(); i++){
					if(i > 3 && i < this.inputListeners.size() - 3)
						if(this.whoIsListening.charAt(this.whoIsListening.length() - 1) != '.') this.whoIsListening += " > ...";
						else continue;
					else
						this.whoIsListening += " > " + this.inputListeners.check(i).getName();
				} return this.whoIsListening;
			}

	// -------
	// BB-CODE
	public void run(){
		do{
			try{Thread.sleep(100);} catch(InterruptedException ie){}
			this.command = new InputGetter(this.whoIsListening() + " >>>");
			switch(this.command.getCommand()){
				case "help": case "h":
					Debuger.pl("help / h \n\t- prints help comment");
					Debuger.pl("exit / e / quit / q \n\t- quits/exits last module");
					Debuger.pl("kill \n\t- kills Atlas");
					break;
				case "kill": Debuger.pl("Killing ATLAS..."); this.inputListeners.erase(); break;
				case "quit": case "q": case "exit": case "e": Debuger.pl("Quitting " + this.inputListeners.checkLast().getName() + "..."); if(this.inputListeners.size() > 0) this.inputListeners.take();
			}
			String cmnd = this.command.getCommand();
			if(this.inputListeners.size() > 0 && !cmnd.equals("exit") && !cmnd.equals("quit") && !cmnd.equals("e") && !cmnd.equals("q")) this.inputListeners.checkLast().execute(this.command);// calls object that implements CommandListener
			if(cmnd.equals("kill") || (this.inputListeners.size() <= 0 && (cmnd.equals("e") || cmnd.equals("q") || cmnd.equals("quit") || cmnd.equals("exit")))) this.running = false;
		} while(this.running);
	}
}