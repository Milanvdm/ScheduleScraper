package cmd;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, IOException, URISyntaxException, ParseException, InterruptedException {

		Cli cli = new Cli();

		Scanner sc = new Scanner(System.in);

		for (System.out.println("Input: "); sc.hasNextLine(); System.out.println("Input: ")) {

			String line = sc.nextLine().replaceAll("\n", "");

			// return pressed
			if (line.length() == 0) 
				continue;

			// split line into arguments
			args = line.split(" "); 

			if(args.length > 1) {
				String[] newArgs = new String[2];

				newArgs[0] = args[0];

				String[] strings = Arrays.copyOfRange(args, 1, args.length);
				StringBuilder builder = new StringBuilder();

				for (String string : strings) {
					if (builder.length() > 0) {
						builder.append(" ");
					}
					builder.append(string);
				}

				String string = builder.toString();

				newArgs[1] = string;
				
				cli.parse(newArgs);
			}
			else {
				cli.parse(args);
			}

		}

		sc.close();




	}
}
