package scraper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import schedule.Course;


public class CourseScraperImpl implements CourseScraper {


	private final static String DUTCH_COURSES_URL = "http://www.kuleuven.be/sapredir/uurrooster/keuze_studiejaar.htm?OBJID_SC=51230411&TAAL=N&SEL_JAAR=2015";
	private final static String ENGLISH_COURSES_URL = "http://www.kuleuven.be/sapredir/uurrooster/keuze_studiejaar.htm?OBJID_SC=52364422&TAAL=N&SEL_JAAR=2015";

	private final static String DUTCH_COURSES_CODE = "51230411";
	private final static String ENGLISH_COURSES_CODE = "51230422";
	
	public List<Course> getCourses(Date weekDate) {
		// TODO Auto-generated method stub
		return null;
	}

	private String getCoursesHtml(String courseUrl, String courseCode, String term, Date weekDate) throws IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		String date = sdf.format(weekDate);
		
		
		Response phaseForm = Jsoup.connect(courseUrl)
				.method(Connection.Method.GET)
				.execute();

		Response loading = Jsoup.connect(phaseForm.url().toString())
				.data("sel_jaar", "2015")
				.data("OTYPE", "SC")
				.data("OBJID", courseCode)
				.data("STUDIEJAAR", term)
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

		return responseWeekSchedule.body();
	}

	private List<String> getCoursesUrls() throws URISyntaxException {
		List<String> urls = new ArrayList<String>();

		urls.add(DUTCH_COURSES_URL);
		urls.add(ENGLISH_COURSES_URL);

		return urls;
	}

	

}
