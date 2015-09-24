package cmd;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
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

			cli.parse(args);

		}
		
		sc.close();




	}
}
