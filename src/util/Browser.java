package util;

import org.openqa.selenium.support.ui.ExpectedCondition;

public interface Browser {
	
	public abstract void waitForRedirection(String url, ExpectedCondition<Boolean> condition);
	
	public abstract String getPageSource();
	
	public abstract void waitForJS(String url);
	
	public abstract Object executeJavascript(String code);
	
	public abstract void closeBrowser();

}
