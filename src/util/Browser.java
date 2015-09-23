package util;

import java.util.Date;

import com.jaunt.NotFound;
import com.jaunt.ResponseException;


public interface Browser {
	
	public abstract String getScheduleHtml(String scheduleUrl, String scheduleId, String term) throws ResponseException, NotFound;
	

}
