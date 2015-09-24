package schedule;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import scraper.CourseScraper;
import scraper.CourseScraperImpl;
import data.CourseData;

public class Schedule {

	private CourseData data = null;;
	private CourseScraper scraper = new CourseScraperImpl();

	private List<Course> courses = new ArrayList<Course>();

	private Date scheduleDate;

	public Schedule() throws ClassNotFoundException, IOException {
		try {
			this.data = readData();
			System.out.println("Read course data.");
		}
		catch(Exception e) {
			data = new CourseData();
			saveData();
			System.out.println("Made new data file.");
		}

		this.scheduleDate = new Date();
	}

	public List<String> getAllCourseNames() {
		return data.getAllCourses();
	}

	public void getCourses() throws URISyntaxException, IOException, ParseException, InterruptedException {

		courses = new ArrayList<Course>();

		Map<String, Course> foundCourses = scraper.getCourses(scheduleDate);

		for(String courseName: data.getAllCourses()) {

			for (Entry<String, Course> e : foundCourses.entrySet()) {
				if (e.getKey().contains(courseName)) {
					courses.add(e.getValue());
				}
			}

		}

		System.out.println("Got info from all courses.");

	}

	public Date getScheduleDate() {
		return scheduleDate;
	}

	public void removeAllData() throws IOException {
		data.removeAllData();
		saveData();
	}

	public void nextWeek() {
		Calendar calender = Calendar.getInstance();
		calender.setTime(scheduleDate);
		calender.add(Calendar.WEEK_OF_YEAR, 1);

		scheduleDate = calender.getTime();
	}

	public void previousWeek() {
		Calendar calender = Calendar.getInstance();
		calender.setTime(scheduleDate);
		calender.add(Calendar.WEEK_OF_YEAR, -1);

		scheduleDate = calender.getTime();
	}

	public void removeCourse(String courseName) throws IOException {
		if(data.containsCourse(courseName)) {

			data.removeData(courseName);

			saveData();

			System.out.println("Removed a course.");
		}
	}

	public void addCourse(String courseName) throws IOException {
		if(!data.containsCourse(courseName)) {

			data.addData(courseName);
			saveData();

			System.out.println("Added a new course.");
		}
	}



	private CourseData readData() throws IOException, ClassNotFoundException {
		CourseData data = null;

		FileInputStream fileIn = new FileInputStream("./CourseData/data.ser");
		ObjectInputStream in = new ObjectInputStream(fileIn);
		data = (CourseData) in.readObject();
		in.close();
		fileIn.close();

		return data;
	}

	private void saveData() throws IOException {
		FileOutputStream fileOut = new FileOutputStream("./CourseData/data.ser");
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(data);
		out.close();
		fileOut.close();
	}

	public String printCourses() {
		StringBuilder sb = new StringBuilder();

		sb.append("===COURSES===");

		for(String courseName: data.getAllCourses()) {

			sb.append("\n");


			sb.append(courseName);



		}

		sb.append("\n");
		sb.append("===END===");

		return sb.toString();
	}

	public String printSchedule() {
		StringBuilder sb = new StringBuilder();

		sb.append("===MAANDAG===");
		sb.append("\n");

		for(Course course: courses) {
			List<CourseMoment> toSort = course.getCourseMomentsOnWeekDay(2); //Sunday is 1

			Collections.sort(toSort);

			for(CourseMoment moment: toSort) {
				sb.append(course.getName());
				sb.append("\n");
				sb.append("\t");
				sb.append(moment.toString());
				sb.append("\n");
			}
		}

		sb.append("\n");
		sb.append("===DINSDAG===");
		sb.append("\n");

		for(Course course: courses) {
			List<CourseMoment> toSort = course.getCourseMomentsOnWeekDay(3); //Sunday is 1

			Collections.sort(toSort);

			for(CourseMoment moment: toSort) {
				sb.append(course.getName());
				sb.append("\n");
				sb.append("\t");
				sb.append(moment.toString());
				sb.append("\n");
			}
		}

		sb.append("\n");
		sb.append("===WOENSDAG===");
		sb.append("\n");

		for(Course course: courses) {
			List<CourseMoment> toSort = course.getCourseMomentsOnWeekDay(4); //Sunday is 1

			Collections.sort(toSort);

			for(CourseMoment moment: toSort) {
				sb.append(course.getName());
				sb.append("\n");
				sb.append("\t");
				sb.append(moment.toString());
				sb.append("\n");
			}
		}

		sb.append("\n");
		sb.append("===DONDERDAG===");
		sb.append("\n");

		for(Course course: courses) {
			List<CourseMoment> toSort = course.getCourseMomentsOnWeekDay(5); //Sunday is 1

			Collections.sort(toSort);

			for(CourseMoment moment: toSort) {
				sb.append(course.getName());
				sb.append("\n");
				sb.append("\t");
				sb.append(moment.toString());
				sb.append("\n");
			}
		}

		sb.append("\n");
		sb.append("===VRIJDAG===");
		sb.append("\n");

		for(Course course: courses) {
			List<CourseMoment> toSort = course.getCourseMomentsOnWeekDay(6); //Sunday is 1

			Collections.sort(toSort);

			for(CourseMoment moment: toSort) {
				sb.append(course.getName());
				sb.append("\n");
				sb.append("\t");
				sb.append(moment.toString());
				sb.append("\n");
			}
		}

		sb.append("\n");
		sb.append("===END===");

		return sb.toString();
	}


	public String printCurrentWeek() {
		Date date = getScheduleDate();

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		Date startDate = cal.getTime();
		cal.add(Calendar.DAY_OF_WEEK, 6);
		Date endDate = cal.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		String toShow = sdf.format(startDate) + " - " + sdf.format(endDate);

		return toShow;
	}





}
