package atlas.tool;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class DataFile{
	
	public class DataFileNotFoundException extends Exception{
		private String fullPath = null;
		public DataFileNotFoundException(String fullPath){this.fullPath = fullPath;}
		public String toString(){return "File not found: " + this.fullPath;}
	}
	public class Reader{
		
		// ------
		// FIELDS
		private File file = null;
		private Scanner dataScaner = null;
		
		// -------
		// METHODS
		public Scanner read(){}
		public Scanner decode(){}
		public Reader(String fileFullPath) throws DataFileNotFoundException{}
	}
	public class Writer{
		
		// ------
		// FIELDS
		private File file = null;
		private FileWriter fw = null;
		
		// -------
		// METHODS
		public void write(){}
		public void encode(){}
		public Writer(String fileFullPath) throws DataFileNotFoundException{}
		public Writer(String fileName, String filePath){}
	}
}