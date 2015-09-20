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


public class UriTest {

	static WebDriver driver;
	private static JavascriptExecutor js;
	static String pageLoadStatus = null;



	public static void main(String[] args) throws URISyntaxException, IOException {

		Capabilities caps = new DesiredCapabilities();
	    ((DesiredCapabilities) caps).setJavascriptEnabled(true);                
	    ((DesiredCapabilities) caps).setCapability("takesScreenshot", false);  
	    ((DesiredCapabilities) caps).setCapability(
	            PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
	            ".\\WebDrivers\\phantomjs.exe"
	        );
	     driver = new PhantomJSDriver(caps);
	     
	     driver.get(courseQuery("Vergelijkende studie van imperatieve"));
		
	     waitForPageToLoad();

		waitSeconds(5);

		System.out.println(driver.getPageSource());
		
		
	}

	public static void waitSeconds(int secons) {
		System.out.print("Pausing for " + secons + " seconds: ");
		try {
			Thread.currentThread();		
			int x = 1;
			while(x <= secons) {
				Thread.sleep(1000);
				System.out.print(" " + x );
				x = x + 1;
			}
			System.out.print('\n');
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}	
	}

	public static void waitForPageToLoad() {
		do {
			js = (JavascriptExecutor) driver;
			pageLoadStatus = (String)js.executeScript("return document.readyState");
			System.out.print(".");
		} while ( !pageLoadStatus.equals("complete") );
		System.out.println();
		System.out.println("Page Loaded.");
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


