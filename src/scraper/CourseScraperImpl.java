package scraper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import schedule.Course;
import schedule.CourseMoment;
import util.Util;


public class CourseScraperImpl implements CourseScraper {

	private Map<String, Course> foundCourses = new HashMap<String, Course>();


	private final static String DUTCH_COURSES_URL = "http://www.kuleuven.be/sapredir/uurrooster/keuze_studiejaar.htm?OBJID_SC=51230411&TAAL=N&SEL_JAAR=2015";
	private final static String ENGLISH_COURSES_URL = "http://www.kuleuven.be/sapredir/uurrooster/keuze_studiejaar.htm?OBJID_SC=52364422&TAAL=N&SEL_JAAR=2015";

	private final static String DUTCH_COURSES_CODE = "51230411";
	private final static String ENGLISH_COURSES_CODE = "51230422";

	public Map<String, Course> getCourses(Date weekDate) throws URISyntaxException, IOException, ParseException {
		foundCourses = new HashMap<String, Course>();

		for(String url: getCoursesUrls()) {
			int phase = 1;

			if(url.equals(DUTCH_COURSES_URL)) {
				while(phase < 3) {
					Document doc = getCoursesHtml(url, DUTCH_COURSES_CODE, Integer.toString(phase), weekDate);
					
					readTable(doc);

					phase++;
				}
			}
			if(url.equals(ENGLISH_COURSES_URL)) {
				while(phase < 3) {
					Document doc = getCoursesHtml(url, ENGLISH_COURSES_CODE, Integer.toString(phase), weekDate);

					readTable(doc);

					phase++;
				}
			}

		}
		
		return foundCourses;
	}

	private void readTable(Document doc) throws ParseException {

		Element table = doc.select("table").get(5); //select the first table.
		Elements rows = table.select("tr");

		Date date = null;
		
		for (Element row: rows.subList(1, rows.size() )) { 
			Elements cols = row.select("td");
		
			for(Element col: cols) {

				if(col.hasAttr("class")) {
					Date tempDate = Util.parseDate(col.text());
					
					if(tempDate != null) {
						date = tempDate;
					}
				}

				Elements courseDescription = col.select("a");

				if(courseDescription.isEmpty()) {
					continue;
				}

				CourseMoment courseMoment = Util.parseCourseDescription(date, courseDescription.first().attr("onmouseover"));

				String courseName = courseDescription.text().trim().replaceAll(" +", " ");

				Course foundCourse = new Course(courseName);
				foundCourse.addCourseMoment(courseMoment);

				addCourse(foundCourse);

			}
		}
	}

	private void addCourse(Course foundCourse) {
		if(foundCourses.containsKey(foundCourse.getName())) { 
			if(!foundCourses.get(foundCourse.getName()).getCourseMoments().contains(foundCourse.getCourseMoments().get(0))) {
				foundCourses.get(foundCourse.getName()).addCourseMoment(foundCourse.getCourseMoments().get(0));
			}
		}
		else {
			foundCourses.put(foundCourse.getName(), foundCourse);
		}

	}

	private Document getCoursesHtml(String courseUrl, String courseCode, String phase, Date weekDate) throws IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		String date = sdf.format(weekDate);


		Response phaseForm = Jsoup.connect(courseUrl)
				.method(Connection.Method.GET)
				.execute();

		Response loading = Jsoup.connect(phaseForm.url().toString())
				.data("sel_jaar", "2015")
				.data("OTYPE", "SC")
				.data("OBJID", courseCode)
				.data("STUDIEJAAR", phase)
				.data("TAAL", "N")
				.cookies(phaseForm.cookies())
				.method(Connection.Method.POST)
				.execute();

		Element loadingForm = loading.parse().getElementsByAttributeValueMatching("name", "ladenform").first();

		Elements inputsLoadingForm = loadingForm.getElementsByTag("input");

		Connection schedule = Jsoup.connect(loading.url().toString());


		for(Element input: inputsLoadingForm) {
			String key = input.attr("name");
			String value = input.attr("value");

			schedule.data(key, value);
		}

		Response responseSchedule = schedule
				.cookies(loading.cookies())
				.method(Connection.Method.POST)
				.execute();

		loading = Jsoup.connect(responseSchedule.url().toString())
				.data("nieuwedatum", date)
				.data("onInputProcessing(nieuwedatum)", "")
				.cookies(responseSchedule.cookies())
				.method(Connection.Method.POST)
				.execute();

		loadingForm = loading.parse().getElementsByAttributeValueMatching("name", "ladenform").first();

		inputsLoadingForm = loadingForm.getElementsByTag("input");

		Connection weekSchedule = Jsoup.connect(loading.url().toString());


		for(Element input: inputsLoadingForm) {
			String key = input.attr("name");
			String value = input.attr("value");

			weekSchedule.data(key, value);
		}

		Response responseWeekSchedule = weekSchedule
				.cookies(loading.cookies())
				.method(Connection.Method.POST)
				.execute();

		return responseWeekSchedule.parse();
	}

	private List<String> getCoursesUrls() throws URISyntaxException {
		List<String> urls = new ArrayList<String>();

		urls.add(DUTCH_COURSES_URL);
		urls.add(ENGLISH_COURSES_URL);

		return urls;
	}



}
