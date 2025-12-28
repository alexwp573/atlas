package atlas.tool.stamper;

import java.util.HashMap;
import java.time.LocalDateTime;
import java.io.Serializable;

public class Stamp implements Serializable{

	// ------------------
	// INSIDE DEFINITIONS
	public enum Type{
		CREATION,
		INITIALIZATION,
		ID,
		CUSTOM
	}
	public static final Type CREATION = Type.CREATION;
	public static final Type INITIALIZATION = Type.INITIALIZATION;
	public static final Type ID = Type.ID;
	public static final Type CUSTOM = Type.CUSTOM;

	// ------
	// FIELDS
	private Stamp.Type type; public Stamp.Type getStampType(){return this.type;}
	private String name; public String getName(){return this.name;}
	private HashMap<Integer, Integer> stamp = new HashMap<>();
		/* DESCRIPTION
		 * 1 - centry
		 * 2 - year
		 * 3 - month
		 * 4 - day
		 * 5 - hour
		 * 6 - minute
		 * 7 - secund
		 * 8 - milisecond
		 * 9 - microsecond
		 * 10 - nanosecond
		 * 11 = extra field 1
		 * 12 = extra field 2
		 * 13 = extra field 3
		 * 14 = extra field 4
		 * 15 = extra field 5
		 * 16 = extra field 6
		 */
		public int size(){return this.stamp.size();}
		public Integer getStamp(int index){return this.stamp.get(index);}

	// -------
	// BB-CODE
	private void stamp(){
		LocalDateTime ldt = LocalDateTime.now();
		this.stamp.put(1, ldt.getYear()/100);
		this.stamp.put(2, ldt.getYear()%100);
		this.stamp.put(3, ldt.getMonthValue());
		this.stamp.put(4, ldt.getDayOfMonth());
		this.stamp.put(5, ldt.getHour());
		this.stamp.put(6, ldt.getMinute());
		this.stamp.put(7, ldt.getSecond());
		this.stamp.put(8, ldt.getNano()/1000000);
		this.stamp.put(9, ldt.getNano()/1000%1000);
		this.stamp.put(10, ldt.getNano()%1000);
	}
	public String toString(){
		String string2return = "";
		for(int i = 1; i < this.stamp.size(); i++){
			string2return += this.stamp.get(i);
			string2return += "-";
		}
		string2return += this.stamp.get(this.stamp.size());
		return string2return;
	}
	public boolean compare(Stamp s2c){
		if(this.type == s2c.getStampType() && this.name.compareTo(s2c.getName()) != 0 ){
			if(this.size() != s2c.size()){return false;}
			for(int i = 0; i < this.stamp.size(); i++){if(this.stamp.get(i+1) != s2c.getStamp(i+1)){return false;}}
			return true;
		}
		return false;
	}

		// ------------
		// CONSTRUCTORS
		public Stamp(){
			this.stamp();
			this.type = Stamp.ID;
		}
		public Stamp(Stamp.Type stampType, String name){
			this.stamp();
			this.type = stampType;
			this.name = name;
		}
		public Stamp(int cen, int yea, int mon, int day, int hou, int min){
			this.stamp.put(1, cen);
			this.stamp.put(2, yea);
			this.stamp.put(3, mon);
			this.stamp.put(4, day);
			this.stamp.put(5, hou);
			this.stamp.put(6, min);
		}
		public Stamp(int cen, int yea, int mon, int day){
			this.stamp.put(1, cen);
			this.stamp.put(2, yea);
			this.stamp.put(3, mon);
			this.stamp.put(4, day);
		}
}