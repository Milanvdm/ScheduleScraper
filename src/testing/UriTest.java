package testing;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import schedule.Course;
import schedule.Schedule;


public class UriTest {




	public static void main(String[] args) throws URISyntaxException, IOException, ParseException, ClassNotFoundException, InterruptedException {

		Schedule schedule = new Schedule();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		
		schedule.addCourse("Vergelijkende studie van imperatieve programmeertalen");
		
		schedule.getCourses();
		
		System.out.println(schedule.printSchedule());
		
		System.exit(0);
	}

	
}


