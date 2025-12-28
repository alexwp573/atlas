package atlas.tool;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.util.HashSet;
import java.io.IOException;

import atlas.tool.*;

public class Logger{

	// -------------------
	// INSIDE DEFIFINITIOS
	public enum Target{
		CONSOLE,
		WINDOW,
		PANEL,
		LOG_WINDOW,
		POP_UP,
		FILE,
		SOCKET
	}

	// ------
	// FIELDS

	private Stamper stamper;
	private HashSet<Logger.Target> targets = new HashSet<>();
		public void addTarget(Target target){this.targets.add(target);}
		public void removeTaret(Target target){this.targets.remove(target);}

	private File logFile;
	private FileWriter writer;

	private JPanel panel;

	// -------
	// BB-CODE

		// ------------
		// CONSTRUCTORS
		public Logger(String fileName){
			this.stamper = new Stamper();
			this.addTarget(Logger.Target.FILE);
			this.logFile = new File(fileName + ".log");
		}
		public Logger(){
			this.stamper = new Stamper();
			this.addTarget(Logger.Target.CONSOLE);
		}
		public Logger(JPanel panel){
			this.stamper = new Stamper();
			this.addTarget(Logger.Target.PANEL);
			this.panel = panel;
			this.panel.setLayout(new GridLayout(0, 1));
		}

		// ----------------------
		// PUBLIC LOGGING METHODS
		public void debg(String msg){this.log("# DEBG @ " + stampLog(msg));}
		public void info(String msg){this.log("# INFO @ " + stampLog(msg));}
		public void warn(String msg){this.log("! WARN @ " + stampLog(msg));}
		public void alrt(String msg){this.log("! ALRT @ " + stampLog(msg));}
		public void dngr(String msg){this.log("X DNGR @ " + stampLog(msg));}
		public void stop(String msg){this.log("X STOP @ " + stampLog(msg));}
		public void errr(String msg){this.log("? ERRR @ " + stampLog(msg));}
		public void crit(String msg){this.log("? CRIT @ " + stampLog(msg));}

		// ------------
		// STAMPING LOG
		private String stampLog(String msg){return Stamper.getNewStamp().toString() + "\n\t\t " + msg;}

		// ----------------------------------------
		// CHOOSING LOG TARGET (logging to console)
		public void log(String logString){
			for(Target target : this.targets){
				switch(target){
					case CONSOLE:
						System.out.println(logString); break;
					case FILE:
						this.writeFile(logString); break;
					case PANEL:
						this.showAtPanel(logString); break;
				}
			}
		}

			// ---------------
			// LOGGING TO FILE
			private boolean writeFile(String msg){
				try{
					this.writer = new FileWriter(this.logFile, true);
					this.writer.write(msg);
					this.writer.write("\n");
					this.writer.close();
				}
				catch(IOException ioe){return false;}
				finally{return true;}
			}

			// ----------------
			// LOGGIN TO JPANEL
			private boolean showAtPanel(String msg){this.panel.add(new Label(msg)); return true;}

}
