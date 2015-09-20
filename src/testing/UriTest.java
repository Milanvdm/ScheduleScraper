package testing;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.*;

import scraper.CourseScraperImpl;


public class UriTest {




	public static void main(String[] args) throws URISyntaxException, IOException {

		CourseScraperImpl test = new CourseScraperImpl();
		
		System.out.println(test.getCourseUrl("Vergelijkende studie van imperatieve"));
		
		System.exit(0);
	}

	
}


