package atlas.tool;

import atlas.tool.stamper.Stamp;

public class Stamper{

	// -------
	// BB-CODE
	public static Stamp getNewStamp(String name){return new Stamp(Stamp.CUSTOM, name);}
	public static Stamp getNewStamp(){return new Stamp();}
}
