package testing;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
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
import scraper.DocumentSettleCondition;
import util.BrowserImpl;
import util.Parser;
import util.Util;


public class UriTest {




	public static void main(String[] args) throws URISyntaxException, IOException {

		BrowserImpl browser = new BrowserImpl();
		
		browser.getUrl("http://www.kuleuven.be/sapredir/uurrooster/pre_laden.htm?OBJID=50448413&OTYPE=SM&TAAL=N&SEL_JAAR=2015");
		
		Boolean check = false;
		
		while(check == false) {
			check = (new WebDriverWait(browser.getDriver(), 10))
					   .until(ExpectedConditions.invisibilityOfElementLocated(By.name("ladenform")));
		}
		
		System.out.println(browser.getPageSource());
		
		browser.executeJavascript("wijzigdatum('26.10.2015')");
		
		browser.waitForPageToLoad();
		
		System.out.println(browser.getPageSource());
		
		System.exit(0);
	}

	
}


