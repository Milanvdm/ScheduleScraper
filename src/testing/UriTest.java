package testing;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.logging.Level;

import org.apache.http.client.utils.URIBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class UriTest {
	
	private static WebDriver driver;

    public static void setUp() {     
        DesiredCapabilities caps = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        driver = new HtmlUnitDriver(caps);
        
    }

    
    public void tearDown() {
        driver.quit();
    }

    public static void analyzeLog() {
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry entry : logEntries) {
            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
            //do something useful with the data
        }
    }

   

	public static void main(String[] args) throws URISyntaxException, IOException {
		
		
		
		WebDriver driver = new HtmlUnitDriver();
		
		driver.get(courseQuery("Vergelijkende studie van imperatieve"));
		
		LogEntries logs = driver.manage().logs().get("browser");
		
		System.out.println(logs.toString());
	}
	

private static String courseQuery(String query) throws URISyntaxException, UnsupportedEncodingException {
	
	URIBuilder uriBuilder = new URIBuilder("https://onderwijsaanbod.kuleuven.be");
	
	URIBuilder queryUri = new URIBuilder();
	queryUri.addParameter("q", query)
			.addParameter("idx", "ALL")
			.addParameter("jaar", "2015")
			.addParameter("isvertaling", "0");
	
	
	uriBuilder	.setPath("/oa/find/")
				.setFragment("/" + queryUri.toString());
	
	
	return uriBuilder.toString().replace("+", "%20");
}


}
