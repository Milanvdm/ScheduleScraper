package util;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Browser {

	private WebDriver driver;
	private JavascriptExecutor js;
	private String pageLoadStatus = null;

	public Browser() {

		Capabilities caps = new DesiredCapabilities();
	    ((DesiredCapabilities) caps).setJavascriptEnabled(true);                
	    ((DesiredCapabilities) caps).setCapability("takesScreenshot", false);  
	    ((DesiredCapabilities) caps).setCapability(
	            PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
	            ".\\WebDrivers\\phantomjs.exe"
	        );
	     
	    driver = new PhantomJSDriver(caps);
	}
	
	public String getPageSourceAfterJS(String url) {
		driver.get(url);
		
	     waitForPageToLoad();

		return driver.getPageSource();
	}
	
	
	public void closeBrowser() {
		driver.close();
	}

	
	private void waitForPageToLoad() {
		
		while ( !pageLoadStatus.equals("complete") ) {
			js = (JavascriptExecutor) driver;
			pageLoadStatus = (String)js.executeScript("return document.readyState");
		} 
	}

	
	
}
