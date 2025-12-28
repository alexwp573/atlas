package atlas.tool.command;

import java.util.Scanner;
import atlas.tool.Command;

public class InputGetter extends Command{

	// ------
	// FIELDS
	private Scanner scanner = new Scanner(System.in);

	// -------
	// BB-CODE
	public InputGetter(String init){
		System.out.print(init + " >>> ");
		Scanner lineScanner = new Scanner(this.scanner.nextLine());
		this.command = "";
		while(lineScanner.hasNext()){
			String token = lineScanner.next();
			switch(token.charAt(0)){
				case ':':
					this.arguments.add(this.getContent(token));
					this.hasArgumentsCount++;
					break;
				case '!':
					this.modificators.add(this.getContent(token));
					this.hasModificatorsCount++;
					break;
				case '-':
					String optionHolder = this.getContent(token);
					Float valueHolder = 1.0f;
					if(lineScanner.hasNextFloat()){
						valueHolder = lineScanner.nextFloat();
					}
					this.options.put(optionHolder, valueHolder);
					this.hasOptionsCount++;
					break;
				default:
					this.command += token;
			}
		}
	}

	private String getContent(String arg){
		String[] splitedStrings = arg.split("[" + arg.charAt(0) + "]");
		return splitedStrings[1];
	}

}
