package atlas.tool;

import atlas.Engine;
import atlas.module.TextUserInterface;

import atlas.tool.Logger;
import atlas.tool.DataStruct;
import atlas.tool.Command;
import atlas.tool.Debuger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.Socket;
import java.net.ServerSocket;
import java.net.URL;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Connection implements Runnable, TextUserInterface.Listener{
	
	private static DataStruct.Table<Integer> portsOccupied = new DataStruct().new Table<>();
		public static boolean isPortOccupied(int port){
			for(int i = 0; i < Connection.portsOccupied.size(); i++){
				if(Connection.portsOccupied.check(i).intValue() == port) return true;
			} return false;}
	
	private Engine engine = null;
	private TextUserInterface tui = null;
	private Logger logger = new Logger("/home/alex/atlas/logs/Connection");
	
	private ServerSocket serverSocket = null;
	private Socket clientSocket = null;
	private InputStream inStream = null;
	private OutputStream outStream = null;
	
	private String myLIP = null;
	private String myPIP = null;
	private String ip = null; public String getIp(){return this.ip;}
	private int port = -1; public int getPort(){return this.port;}
	private boolean running = false;
	private boolean listening = false;
	
	public String getName(){return "Connection";}
	public void execute(Command cmnd){
		switch(cmnd.getCommand()){
			case "ip": try{
					Debuger.pl("Getting your IPs...");
					this.myLIP = this.myLIP();
					this.myPIP = this.myPIP();
					Debuger.db("IPs acquired", "Press enter to see your IPs");
					Debuger.pl("Your local IP: " + this.myLIP);
					Debuger.pl("Your public IP: " + this.myPIP);}
				catch(UnknownHostException  e){Debuger.pl(e.toString());}
				catch(Exception e){Debuger.pl(e.toString());} break;
			case "connect": if(cmnd.amoCheck(1, 0, 1)){
					this.ip = cmnd.getArguments().get(0);
					this.port = (int)(cmnd.getValues().get("port").floatValue());
					this.connect();
				} else{Debuger.pl("Not enough arguments, modificators, options or values!");} break;
			case "listen": if(cmnd.amoCheck(0, 0, 1)){
					this.port = (int)(cmnd.getValues().get("port").floatValue());
					this.listen();
				} else{Debuger.pl("Not enough arguments, modificators, options or values!");} break;
			case "disconnect": this.disconnect(); break;
			default: Debuger.pl("Unknown command...");
		}
	}
	
	public Connection(Engine engine, String ip, int port){
		this.engine = engine; this.ip = ip; this.port = port;
		Connection.portsOccupied.add(Integer.valueOf(port));
	}
	public Connection(Engine engine, int port){
		this.engine = engine; this.ip = null; this.port = port;
		Connection.portsOccupied.add(Integer.valueOf(port));
	}
	public Connection(Engine engine){
		this.engine = engine; this.tui = this.engine.getTui();}
	
	public void run(){
		if(this.ip != null && this.port != -1) this.connect();
		else if(this.ip == null && this.port != -1) this.listen();
		else if(this.ip == null && this.port == -1){this.logger.addTarget(Logger.Target.CONSOLE); this.tui.addInListener(this);}}
	
	private String myLIP() throws UnknownHostException{
		try{ return InetAddress.getLocalHost().getHostAddress();} catch(UnknownHostException e){throw e;}}
	private String myPIP() throws Exception{
		BufferedReader br = null; try{
			URL url = new URL("https://checkip.amazonaws.com");
			br = new BufferedReader(new InputStreamReader(url.openStream()));
		} catch (Exception e){ throw e;} return br.readLine();}
	private void listen(){
		try{this.serverSocket = new ServerSocket(this.port);
			this.logger.info("Server is listening on port: " + this.port);
			while(!Thread.currentThread().isInterrupted()){
				Socket clientSocket = serverSocket.accept();
				this.logger.info("Client connected: " + clientSocket.getInetAddress().getHostAddress());}}
		catch(IOException ioe){ if(this.serverSocket != null && !this.serverSocket.isClosed()) this.logger.errr(ioe.toString());}
		finally{this.close();}}
	private void connect(){
		try{this.clientSocket = new Socket(ip, port);
			this.inStream = this.clientSocket.getInputStream();
			this.outStream = this.clientSocket.getOutputStream();
			this.logger.debg("Connected to server: " + ip + ":" + port);}
		catch(IOException ioe){this.logger.errr(ioe.toString());}}
	private void disconnect(){
		this.close();}
	
	public byte[] get(){
		byte[] buffer = new byte[1024];
		try{if(this.inStream != null){
			int bytesRead = this.inStream.read(buffer);
			if(bytesRead == -1) return null;
			byte[] receivedData = new byte[bytesRead];
			System.arraycopy(buffer, 0, receivedData, 0, bytesRead);
			return receivedData;}}
		catch(IOException ioe){this.logger.errr(ioe.toString());} return null;}
	public void send(byte[] data){
		try{if(this.outStream != null){this.outStream.write(data); outStream.flush();}}
		catch(IOException ioe){this.logger.errr(ioe.toString());}}
	
	public void close(){
		try{if(this.inStream != null) this.inStream.close();
			if(this.outStream != null) this.outStream.close();
			if(this.clientSocket != null) this.clientSocket.close();
			if(this.serverSocket != null) this.serverSocket.close();}
		catch(IOException ioe){this.logger.errr(ioe.toString());}
		for(int i = 0; i < Connection.portsOccupied.size(); i++) if(Connection.portsOccupied.check(i).compareTo(Integer.valueOf(this.port)) == 0) Connection.portsOccupied.pull(i);
	}
}