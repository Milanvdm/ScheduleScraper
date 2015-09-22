package util;

import java.io.IOException;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
public class BrowserImpl implements Browser {

	private WebDriver driver;
	private JavascriptExecutor js;
	private String pageLoadStatus = null;
	
	private final static String REDIRECTION_STRING
				= "<!-- LADEN VAN DE TOEPASSING MIJN UURROOSTERS    -->";

	public BrowserImpl() {

		Capabilities caps = new DesiredCapabilities();
		((DesiredCapabilities) caps).setJavascriptEnabled(true);                
		((DesiredCapabilities) caps).setCapability("takesScreenshot", false);  
		((DesiredCapabilities) caps).setCapability(
				PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
				".\\WebDrivers\\phantomjs.exe"
				);

		driver = new PhantomJSDriver(caps);
	}
	
	public void waitForRedirection(String url) throws InterruptedException, IOException {
		getUrl(url);
		
		while(Parser.getFirstLine(driver.getPageSource()).contains(REDIRECTION_STRING) || driver.getPageSource().length() < 15000) {
			Thread.sleep(500);
		}
	}
	
	private void getUrl(String url) {
		driver.get(url);
	}
	
	public String getPageSource() {
		return driver.getPageSource();
	}

	public void waitForJS(String url) {
		getUrl(url);

		waitForPageToLoad();

		Util.waitSeconds(3);
	}


	public void closeBrowser() {
		driver.close();
	}

	public Object executeJavascript(String code) {
		js = (JavascriptExecutor) driver;

		return js.executeScript(code);
	}


	private void waitForPageToLoad() {

		do {
			pageLoadStatus = (String) executeJavascript("return document.readyState");
		} while ( !pageLoadStatus.equals("complete") );
	}



}
