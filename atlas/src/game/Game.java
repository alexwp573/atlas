package atlas.game;

public interface Game extends Runnable{
	public String getName();
	public boolean load() throws GameDataErrorException;
	public boolean load(int back) throws GameDataErrorException, PastSaveNotExistsException;
	
	public class GameDataErrorException extends Exception{
		private String errorMessage = null;
		private String fileErrorName = null;
		
		public GameDataErrorException(String message){this.errorMessage = message;}
		public GameDataErrorException(String message, String pathToFileWithError){this.errorMessage = message; this.fileErrorName = pathToFileWithError;}
		
		public String toString(){return this.fileErrorName == null ? "" : ("File: " + this.fileErrorName + ": ") + this.errorMessage;}
	}
	
	public class PastSaveNotExistsException extends Exception{
		public int pastSave = 0;
		
		public PastSaveNotExistsException(int pastSave){this.pastSave = pastSave;}
		
		public String toString(){return this.pastSave + " in past does not exists";}
}
}
