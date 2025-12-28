package atlas.tool;

import java.util.Scanner;

public class Debuger{

	public static void db(String done, String next){
		System.out.print(done);
		System.out.print("\n\t--- next:\n");
		System.out.print(next);
		System.out.print("...");
		Debuger.bp();
	}

	public static void bp(){Scanner scan = new Scanner(System.in);scan.nextLine();}

	public static void pl(String dataToPrint){System.out.println(dataToPrint);}

}