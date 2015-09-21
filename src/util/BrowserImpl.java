package util;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserImpl implements Browser {

	private WebDriver driver;
	private JavascriptExecutor js;
	private String pageLoadStatus = null;

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
	
	public void waitForRedirection(String url, ExpectedCondition<Boolean> condition) {
		getUrl(url);
		
		Boolean check = false;
		
		while(check == false) {
			check = (new WebDriverWait(driver, 10))
					   .until(condition);
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

		Util.waitSeconds(1);
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
