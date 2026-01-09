package atlas.tool;

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
			this.command = new InputGetter(this.whoIsListening());
			switch(this.command.getCommand()){
				case "help": case "h":
					Debuger.pl("--------\nATLAS control:");
					Debuger.pl("Command formula:\n\t >>> command [:argument] [!modificator] [-option [value]] [-option [value]] ...");
					Debuger.pl("help / h \n\tprints help comment");
					Debuger.pl("clear / c \n\tclears the screen");
					Debuger.pl("exit / e / quit / q \n\tquits/exits last module");
					Debuger.pl("kill / k \n\tkills Atlas");
					Debuger.pl("--------\nModule control:");
					break;
				case "kill": case "k": Debuger.pl("Killing ATLAS..."); this.inputListeners.erase(); break;
				case "clear": case "c": System.out.print("\033[2J"); System.out.print("\033[H"); break;
				case "quit": case "q": case "exit": case "e": Debuger.pl("Quitting " + this.inputListeners.checkLast().getName() + "..."); if(this.inputListeners.size() > 0) this.inputListeners.take();
			}
			String cmnd = this.command.getCommand();
			if(!cmnd.equals("clear") && !cmnd.equals("c") && this.inputListeners.size() > 0 && !cmnd.equals("exit") && !cmnd.equals("quit") && !cmnd.equals("e") && !cmnd.equals("q")) this.inputListeners.checkLast().execute(this.command);// calls object that implements CommandListener
			if(cmnd.equals("kill") || cmnd.equals("k") || (this.inputListeners.size() <= 0 && (cmnd.equals("e") || cmnd.equals("q") || cmnd.equals("quit") || cmnd.equals("exit")))) this.running = false;
		} while(this.running);
	}
}