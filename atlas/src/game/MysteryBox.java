package atlas.game;

import atlas.tool.Debuger;
import atlas.tool.Logger;
import atlas.tool.Command;
import atlas.tool.DataStruct;
import atlas.tool.TextUserInterface;
import atlas.tool.TextUserInterface.Listener;

import atlas.game.Game;
import atlas.game.GameLauncher;

public class MysteryBox implements Listener, Game, Runnable{
	
	private enum Rarity{
		PLAIN,
		COMMON,
		RARE,
		EPIC,
		LEGENDARY,
		MYSTIC
	}
	private class Box{
		private Rarity rarity = null;
		private Item item = null;
		public Item openBox(){
			int itemType = (int)(Math.random() * 5);
			int itemIndex = (int)(Math.random() * 100000) + 1;
			int itemQuality = (int)(Math.random() * 100) + 1;
			
			if(itemIndex >= 100000) this.rarity = Rarity.PLAIN;
			else if(itemIndex >= 10000) this.rarity = Rarity.COMMON;
			else if(itemIndex >= 1000) this.rarity = Rarity.RARE;
			else if(itemIndex >= 100) this.rarity = Rarity.EPIC;
			else if(itemIndex >= 10) this.rarity = Rarity.LEGENDARY;
			else this.rarity = Rarity.MYSTIC;
		
			switch(itemType){
				case 0: this.item = new Book(); break;
				case 1: this.item = new Weapon(); break;
				case 2: this.item = new Phone(); break;
				case 3: this.item = new Vinyl(); break;
				case 4: this.item = new LeagueOfLegendsChampionStatue(); break;
			}
			return this.item;
		}
	}
	private class Item{
		public static final Rarity PLAIN_RARITY = Rarity.PLAIN;
		public static final Rarity COMMON_RARITY = Rarity.COMMON;
		public static final Rarity RARE_RARITY = Rarity.RARE;
		public static final Rarity EPIC_RARITY = Rarity.EPIC;
		public static final Rarity LEGENDARY_RARITY = Rarity.LEGENDARY;
		public static final Rarity MYSTIC_RARITY = Rarity.MYSTIC;
		private String name = null; public String getName(){return this.name;}
		private Rarity rarity = null; public Rarity getRarity(){return this.rarity;}
		private int quality = 0; public int getQuality(){return this.quality;}
		public Item(Rarity rrty, int qlty){this.rarity = rrty; this.quality = qlty;}
	}
	private class Book extends Item{
		private static void DataStruct.Pair<> names = new DataStruct().new Table<>();
		public static void init(String[] names){for(int i = 0; i < names.length; i++) Book.names.add(names[i]);}
		public Book(Rarity rrty, int qlty){
			super(rrt, qlty);
			
		}
	}
	private class Weapon extends Item{}
	private class Phone extends Item{}
	private class Vinyl extends Item{}
	private class LeagueOfLegendsChampionStatue extends Item{}
	
	private GameLauncher gl = null;
	private TextUserInterface tui = null;
	private Logger logger = new Logger("/home/alex/atlas/logs/MysteryBox");
	
	public MysteryBox(GameLauncher gl){this.gl = gl; this.tui = this.gl.getTui();}
	
	private DataStruct.Table<Box> boxes = new DataStruct().new Table<Box>();
	private DataStruct.Table<Book> books = new DataStruct().new Table<Book>();
	private DataStruct.Table<Weapon> weapons = new DataStruct().new Table<Weapon>();
	private DataStruct.Table<Phone> phones = new DataStruct().new Table<Phone>();
	private DataStruct.Table<Vinyl> vinyls = new DataStruct().new Table<Vinyl>();
	private DataStruct.Table<LeagueOfLegendsChampionStatue> leagueOfLegendsStatues = new DataStruct().new Table<LeagueOfLegendsChampionStatue>();
	
	private void list(Command cmnd){
		if(!cmnd.getArguments().isEmpty())
			for(int i = 0; i < cmnd.getArguments().length(); i++)
				switch(cmnd.getArguments().get(i)){
					case "book":
						for(int i = 0; i < this.books.size(); i++) Debuger.pl(this.books.check(0).toString());
						break;
					case "weapon":
						for(int i = 0; i < this.wepons.size(); i++) Debuger.pl(this.wepons.check(0).toString());
						break;
					case "phone":
						for(int i = 0; i < this.phones.size(); i++) Debuger.pl(this.phones.check(0).toString());
						break;
					case "vinyl":
						for(int i = 0; i < this.vinyls.size(); i++) Debuger.pl(this.vinyls.check(0).toString());
						break;
					case "lolChamp":
						for(int i = 0; i < this.leagueOfLegendsStatues.size(); i++) Debuger.pl(this.leagueOfLegendsStatues.check(0).toString());
						break;
					default: Debuger.pl("Unknown item type...");
				}
		else {
			Debuger.pl("--- Books:");
			for(int i = 0; i < this.books.size(); i++) Debuger.pl(this.books.check(0).toString());
			Debuger.pl("--- Weapons:");
			for(int i = 0; i < this.wepons.size(); i++) Debuger.pl(this.wepons.check(0).toString());
			Debuger.pl("--- Phones:");
			for(int i = 0; i < this.phones.size(); i++) Debuger.pl(this.phones.check(0).toString());
			Debuger.pl("--- Vinyls:");
			for(int i = 0; i < this.vinyls.size(); i++) Debuger.pl(this.vinyls.check(0).toString());
			Debuger.pl("--- League of Legends Champion Statues:");
			for(int i = 0; i < this.leagueOfLegendsStatues.size(); i++) Debuger.pl(this.leagueOfLegendsStatues.check(0).toString());
		}
	}
	
	// ----------
	// OVERWRITES
	public void run(){this.tui.addInListener(this);}
	public void execute(Command cmnd){
		switch(cmnd.getCommand()){
			case "list": this.list(cmnd); break;
			case "open": break;
			case "check": break;
			default: Debuger.pl("Unknown command...");
		}
	}
	public String getName(){return "MysteryBox";}
	public boolean load() throws GameDataErrorException{return false;}
	public boolean load(int back) throws GameDataErrorException, PastSaveNotExistsException{return false;}
}