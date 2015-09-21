package testing;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.utils.URIBuilder;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.*;

import scraper.CourseScraperImpl;
import util.BrowserImpl;
import util.Parser;
import util.Util;


public class UriTest {




	public static void main(String[] args) throws URISyntaxException, IOException, ParseException {

		Date date = Parser.parseDate("Donderdag<BR>24.09.2015<BR>");

		
		System.out.println(date.toString());
		
		System.exit(0);
	}

	
}


