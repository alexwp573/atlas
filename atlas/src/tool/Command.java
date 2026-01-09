package atlas.tool;

import java.util.ArrayList;
import java.util.HashMap;

public class Command{

	// ------
	// FIELDS

		// -------------
		// COMMAND (com)
		protected String command;
			public String getCommand(){return this.command;}

		// ---------------
		// ARGUMENT (:arg)
		protected ArrayList<String> arguments = new ArrayList<>();
			public ArrayList<String> getArguments(){return this.arguments;}
				protected int hasArgumentsCount = 0;
					public int hasArguments(){return this.hasArgumentsCount;}

		// -------------------
		// MODIFICATORS (!mod)
		protected ArrayList<String> modificators = new ArrayList<>();
			public ArrayList<String> getModificators(){return this.modificators;}
				protected int hasModificatorsCount = 0;
					public int hasModificators(){return this.hasModificatorsCount;}

		// -------------------------
		// OPTION & VALUE (-opt val)
		protected HashMap<String, Float> options = new HashMap<>();
			public ArrayList<String> getOptions(){ArrayList<String> arrayListToReturn = new ArrayList<>(this.options.keySet()); return arrayListToReturn;}
			public HashMap<String, Float> getValues(){return this.options;}
				protected int hasOptionsCount = 0;
					public int hasOptions(){return this.hasOptionsCount;}

	// -------
	// BB-CODE
	public String toString(){
		String stringToReturn = this.command;
		for(String arg : this.arguments){stringToReturn += arg;}
		for(String mod : this.modificators){stringToReturn += mod;}
		for(int i = 0; i < this.hasOptions(); i++){stringToReturn += this.getOptions().get(i); stringToReturn += this.getValues().get(i);}
		return stringToReturn;
	}
	public boolean amoCheck(int a, int m, int o, int v){
		if(this.arguments.size() >= a && this.modificators.size() >= m && this.options.size() >= o) return true; return false;
	}

}
