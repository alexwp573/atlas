package atlas.tool;

// for connection
import atlas.Engine;
import atlas.module.TextUserInterface;
import atlas.tool.Logger;

import java.io.IOException; // ClientHandler, Server
import java.net.Socket; //ClientHadnler, Server
import java.net.ServerSocket; // Server
import atlas.tool.DataStruct; // Server

// -------------
// INNER CLASSES
private class Server implements Runnable{
	
	// --------------
	// PRIVATE FIELDS
	private final int PORT;
	private ServerSocket serverSocket = null;
	private final DataStruct.Table<ClientHandler> clients = new DataStruct().new Table<>();
	
	// ---------------
	// PRIVATE MOTHODS
	private void closeServer(){
		try{if(this.serverSocket != null && !this.serverSocket.isClosed();) this.serverSocket.close(); this.clients.erase();}
		catch(IOException ioe){Connection.logger.errr(ioe.toString());}
	}
	
	// --------------
	// PUBLIC MOTHODS
	public void run(){
		try{this.serverSocket = new ServerSocket(this.PORT);
			Connection.logger.info("Server is listening on port: " + this.PORT);
			while(!Thread.currentThread().isInterrupted()){
				Socket clientSocket = serverSocket.accept();
				Connection.logger.info("New client connected: " + clientSocket.getInetAddress().getHostAddress());
				ClientHandler clientHandler = new ClientHandler(clientSocket);
				clients.add(clientHandler);
				new Thread(clientHandler}.start();}
		catch(IOException ioe){
			if(this.serverSocket != null && !this.serverSocket.isClosed())
				Connection.logger.errr(ioe.toString);}
		finally{this.closeServer();}
	}
	
		// ------------
		// CONSTRUCTORS
		public Server(int port){this.PORT = port;}
}








public class Connection implements Runnable, Listener{
		
	// -------------
	// INNER CLASSES
	private class ClientHandler implements Runnable{
		
		// --------------
		// PRIVATE FIELDS
		private Socket clientSocket = null;
		private InputStream inStream = null;
		private OutputStream outStream = null;
		
		// --------------
		// PUBLIC METHODS
		public void run(){
			try{while(!clientSocket.siClosed()){Thread.sleep(1000);}}
			catch(InterruptedException ie){Thread.currentThread().interrupt();}
			finally{closeConnection();}
		}
		public void send(byte[] data){
			try{this.outStream.write(data); this.outStream.flush();}
			catch(IOException ioe){Connection.logger.alrt(ioe.toString());}
		}
		public byte[] get(){
			byte[] buffer = new byte[1024];
			int bytesRead = 0;
			try{bytesRead = this.inStream.read(buffer);
				if(bytesRead == -1){return null;}
				byte[] receivedData = new byte[bytesRead];
				System.arraycopy(buffer, 0, receivedData, 0, bytesRead);
				return receivedData;}
			catch(IOException ioe){Connection.logger.errr(ioe.toString()); return null;}
		}
		public void closeConnection(){
			try{if(inStream != null) inStream.close();
				if(outStream != null) outStream.close();
				if(clientSocket != null) clientSocket.close();}
			catch(IOException ioe){Connection.logger.errr(ioe.toString());}
		}
			// ------------
			// CONSTRUCTORS
			public ClientHandler(Socket clientSocket){
				this.clientSocket = clientSocket;
				try{this.inStream = this.clientSocket.getInputStream(); this.outStream = this.clientSocket.getOutputStream();}
				catch(IOException ioe){Connection.logger.errr(ioe.toString();}
			}
	}


	// --------------
	// PRIVATE FIELDS
	private Engine engine = null;
	private TextUserInterface tui = null;
	private Logger logger = new Logger("/home/alex/atlas/logs/Connection");
	private Socket socket = null;
	private InputStream inStream = null;
	private OutpurStream outStream = null;
	
	// --------------
	// PUBLIC METHODS
	public void connect(String ip, int port){
		try{this.socket = new Socket(ip, port);
			this.inStream = this.socket.getInputStream();
			this.outStream = this.socket.getOutputStream();
			this.logger.debg("Connected to server: " + ip + ":" + port);}
		catch(IOException ioe){this.logger.errr(ioe.toString|();}
	}
	public void send(byte[] data){
		try{if(this.outStream != null){this.outStream.write(data); outStream.flush();}}
		catch(IOException ioe){Connection.logger.errr(ioe.toString());}}
	public byte[] get(){
		try{if(this.inStream != null){
			byte[] buffer = new byte[1024];
			int bytesRead = this.inStream.read(buffer);
			if(bytesRead = -1) return null;}
			byte[] receivedData = new byte[bytesRead];
			System.arraycopy(buffer, 0, receivedData, 0, bytesRead);
			return receivedData;}
		catch(IOException ioe) this.logger.errr(ioe.toString());
		return null;}
	public void close(){
		try{if() this.inStream.close();
			if() outStream.close();
			if() this.socket.close();
			this.logger.info("Connection closed.");}
		catch(IOException ioe) this.logger.errr(ioe.toString());}

		// ------------
		// CONSTRUCTORS
		public Connection(Engine engine){this.engine = engine; this.tui = this.engine.getTui();}
}