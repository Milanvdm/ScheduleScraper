package cmd;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import schedule.Schedule;

public class Cli {
	private Schedule schedule;
	
	private Options options = new Options();

	public Cli() throws ClassNotFoundException, IOException {
		
		schedule = new Schedule(); //Dont start browser when schedule is made.

		options.addOption("h", "help", false, "show help.");
		options.addOption("e", "help", false, "exit.");
		
		options.addOption("nw", "nextWeek", false, "go to next week.");
		options.addOption("pw", "prevWeek", false, "go to previous week.");
		
		options.addOption("ps", "printSchedule", false, "print schedule for current week.");
		options.addOption("pc", "printCourses", false, "print an overview of your added courses.");
		
		options.addOption("ac", "addCourse", true, "add a course.");
		options.addOption("rc", "removeCourse", true, "remove a course.");
		
		Option linkOption = new Option("lc", "linkCourse", true, "link a course to another one.");
		linkOption.setArgs(2);
		options.addOption(linkOption);
		
		Option unlinkOption = new Option("uc", "unlinkCourse", true, "unlink a course from another one.");
		unlinkOption.setArgs(2);
		options.addOption(unlinkOption);
		
		System.out.println("Current week is: " + schedule.printCurrentWeek());
		help();

	}
	

	public void parse(String[] args) throws IOException, URISyntaxException, java.text.ParseException, InterruptedException {
		CommandLineParser parser = new DefaultParser();

		CommandLine cmd = null;
		try {
			cmd = parser.parse(options, args);

			if (cmd.hasOption("h")) {
				help();
			}
			
			if (cmd.hasOption("e")) {
				System.exit(0);
			}

			if (cmd.hasOption("nw")) {
				schedule.nextWeek();
				System.out.println("Current week is: " + schedule.printCurrentWeek());
			}
			
			if (cmd.hasOption("pw")) {
				schedule.previousWeek();
				System.out.println("Current week is: " + schedule.printCurrentWeek());
			}
			
			if (cmd.hasOption("ps")) {
				schedule.getCourses();
				
				String toPrint = schedule.printSchedule();
				
				System.out.println(toPrint);
			}
			
			if (cmd.hasOption("pc")) {
				String toPrint = schedule.printCourses();
				
				System.out.println(toPrint);
			}
			
			if (cmd.hasOption("ac")) {
				String courseName = cmd.getOptionValue("ac");
				
				schedule.addCourse(courseName);
			}
			
			if (cmd.hasOption("rc")) {
				String courseName = cmd.getOptionValue("rc");
				
				schedule.removeCourse(courseName);
			}
			
			if (cmd.hasOption("lc")) {
				String[] options = cmd.getOptionValues("lc");
				
				schedule.linkCourses(options[0], options[1]);
			}
			
			if (cmd.hasOption("uc")) {
				String[] options = cmd.getOptionValues("uc");
				
				schedule.unlinkCourses(options[0], options[1]);
			}
			

		} catch (ParseException e) {
			help();
		}
	}

	private void help() {
		// This prints out some help
		HelpFormatter formater = new HelpFormatter();

		formater.printHelp("Main", options);
	}
}
