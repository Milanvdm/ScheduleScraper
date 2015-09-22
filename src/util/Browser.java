package util;

import java.io.IOException;


public interface Browser {
	
	public abstract void waitForRedirection(String url) throws InterruptedException, IOException;
	
	public abstract String getPageSource();
	
	public abstract void waitForJS(String url);
	
	public abstract Object executeJavascript(String code);
	
	public abstract void closeBrowser();

}
