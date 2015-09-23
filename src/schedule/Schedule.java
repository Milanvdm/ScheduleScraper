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

import scraper.CourseScraper;
import scraper.CourseScraperImpl;
import data.CourseData;
import data.CourseTriple;

public class Schedule {

	private CourseData data = null;;
	private CourseScraper scraper;

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
		scraper = new CourseScraperImpl();

		courses = new ArrayList<Course>();

		checkUrls();

		for(String courseName: data.getAllCourses()) {
			CourseTriple triple = data.getCourseTriple(courseName);

			if(triple.isLinked()) {
				continue;
			}

			String courseUrl = triple.getUrl();

			Course course = new Course(courseName, courseUrl);

			List<CourseMoment> toAdd = scraper.getCourseMoments(courseUrl, scheduleDate);

			course.addCourseMoments(toAdd);


			if(triple.getLinkedCourse() != null) {
				CourseTriple linkedCourseTriple = data.getCourseTriple(triple.getLinkedCourse());

				course.addCourseMoments(scraper.getCourseMoments(linkedCourseTriple.getUrl(), scheduleDate));

				System.out.println("Added linked course-moments.");
			}

			courses.add(course);
		}

		System.out.println("Got info from all courses.");

		scraper.close();
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
			if(data.getCourseTriple(courseName).isLinked()) {
				CourseTriple linkedTo = data.getLinkedTo(courseName);
				unlinkCourses(linkedTo.getName(), courseName);
			}

			data.removeData(data.getCourseTriple(courseName));

			saveData();

			System.out.println("Removed a course.");
		}
	}

	public void addCourse(String courseName) throws IOException {
		if(!data.containsCourse(courseName)) {
			CourseTriple triple = new CourseTriple(courseName);
			data.addData(triple);
			saveData();

			System.out.println("Added a new course.");
		}
	}

	public void checkUrls() throws URISyntaxException, IOException {
		scraper = new CourseScraperImpl();

		for(String courseName: data.getAllCourses()) {
			if(!data.containsUrl(courseName)) {
				String courseUrl = scraper.getCourseUrl(courseName);

				data.getCourseTriple(courseName).setUrl(courseUrl);
			}
		}

		saveData();

		System.out.println("Checked urls.");

		scraper.close();
	}

	public void linkCourses(String courseName, String toBeLinked) throws IOException {
		if(data.containsCourse(toBeLinked)) {
			data.getCourseTriple(courseName).setLinkedCourse(toBeLinked);

			data.getCourseTriple(toBeLinked).setLinked(true);

			saveData();
		}

	}

	public void unlinkCourses(String courseName, String toBeUnLinked) throws IOException {
		if(data.containsCourse(toBeUnLinked)) {
			data.getCourseTriple(courseName).setLinkedCourse(null);

			data.getCourseTriple(toBeUnLinked).setLinked(false);

			saveData();
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

			if(data.getCourseTriple(courseName).isLinked() == true) {
				continue;
			}

			sb.append("\n");

			if(data.getCourseTriple(courseName).getLinkedCourse() != null) {
				sb.append("[" + courseName + ", " + data.getCourseTriple(courseName).getLinkedCourse() + "]");
			}
			else {
				sb.append(courseName);
			}


		}

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
