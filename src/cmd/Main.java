package cmd;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, IOException, URISyntaxException, ParseException, InterruptedException {
		
		new Cli(args).parse();
		
	}


}
