package atlas.tool;

import java.util.Scanner;

import atlas.tool.DataStruct;

public class DataHolder{
	
	public class DataUnreadableException{
		
		private String dataExpected = null;
		private String dataDelivered = null;
		
		public DataUnreadableException(String expected, String delivered){
			this.dataExpected = expected;
			this.dataDelivered = delivered;
		}
		
		public String toString(){return "Expected data type: " + this.dataExpected + ", data delivered: " + this.dataDelivered;}
		
	}
	
	private DataStruct.Table<Pair<String, DataHolder>> dhs = null;
		private DataHolder getDH(String name){for(int i = 0; i < this.dhs.size(); i++) if(this.dhs.check(i).getKey() == name) return this.dhs.check(i).getVal(); return null;}
	private DataStruct.Table<Pair<String, Integer>> ints = null;
		private Integer getInt(String name){for(int i = 0; i < this.ints.size(); i++) if(this.ints.check(i).getKey() == name) return this.ints.check(i).getVal(); return null;}
	private DataStruct.Table<Pair<String, Float>> floats = null;
		private Float getFloat(String name){for(int i = 0; i < this.floats.size(); i++) if(this.floats.check(i).getKey() == name) return this.floats.check(i).getVal(); return null;}
	private DataStruct.Table<Pair<String, Boolean>> bools = null;
		private Boolean getBool(String name){for(int i = 0; i < this.bools.size(); i++) if(this.bools.check(i).getKey() == name) return this.bools.check(i).getVal(); return null;}
	private DataStruct.Table<Pair<String, String>> strings = null;
		private String getString(String name){for(int i = 0; i < this.strings.size(); i++) if(this.strings.check(i).getKey() == name) return this.strings.check(i).getVal(); return null;}
	private Scanner stringDataScanner = null;
	
	public DataHolder(Scanner dataScanner) throws DataUnreadableException {
		this.stringDataScanner = dataScanner;
		String token = null;
		String data = null;
		while(this.stringDataScanner.hasNext()){
			token = this.stringDataScanner.next();
			switch(token){
				case "set":
					 this.dhs.add(new Pair(this.stringDataScanner.next(), new DataHolder(this.stringDataScanner)));
					 break;
				case "string":
					String key = this.stringDataScanner.next();
					String val = this.stringDataScanner.next();
					if(val.equals("{") == 0){
						val = this.stringDataScanner.next();
						String next = this.stringDataScanner.next();
						while(next.equals("}") != 0){
							val += (" " + next);
							next = this.stringDataScanner.next();}}
					this.strings.add(new Pair(key, val)); break;
				case "int": 
					String key = this.stringDataScanner.next();
					if(this.stringDataScanner.hasNextInt())
						this.ints.add(key, Integer.valueOf(this.stringDataScanner.nextInt()));
					else throw new DataUnreadableException("int", this.stringDataScanner.next()); break;
				case "float":
					String key = this.stringDataScanner.next();
					if(this.stringDataScanner.hasNextFloat())
						this.ints.add(key, Float.valueOf(this.stringDataScanner.nextFloat()));
					else throw new DataUnreadableException("float", this.stringDataScanner.next()); break;
				case "bool":
					String key = this.stringDataScanner.next();
					String val = this.stringDataScanner.next();
					if(val.compareToIgnoreCase("true")==0 || val.compareToIgnoreCase("t")==0 || val.compareToIgnoreCase("1")==0){
						this.bools.add(key, Boolean.valueOf(true)); break;}
					if(val.compareToIgnoreCase("false")==0 || val.compareToIgnoreCase("f")==0 || val.compareToIgnoreCase("0")==0){
						this.bools.add(key, Boolean.valueOf(false)); break;}
					throw new DataUnreadableException("float", this.stringDataScanner.next());
				case "[": 
					String skipped = null;
					do{skipped = this.stringDataScanner.next()} while(skipped.compareTo("]")); break;
				default: continue;
			}
		}
	}
}