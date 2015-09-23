package util;


import java.text.SimpleDateFormat;
import java.util.Date;

import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;
import com.jaunt.component.Form;

public class BrowserImpl implements Browser {

	private UserAgent userAgent = new UserAgent(); 

	public String getScheduleHtml(String scheduleUrl, String scheduleId, String term) throws ResponseException, NotFound {
		userAgent.visit(scheduleUrl);

		Form uurroosterform = userAgent.doc.getForm("<form name=uurroosterform>");

		uurroosterform.setHidden("OTYPE", "SC");      
		uurroosterform.setHidden("OBJID", scheduleId);    
		uurroosterform.setHidden("STUDIEJAAR", term);
		uurroosterform.setHidden("TAAL", "N");
		uurroosterform.submit();  

		String sessionId = null;

		Elements inputs = userAgent.doc.getForm("<form name=ladenform>").getElement().findEach("<input>");

		for(Element input: inputs) {
			if(input.getAt("name").equals("sessionid")) {
				sessionId = input.getAt("value");
			}
		}

		userAgent.doc.getForm("<form name=ladenform>").submit();


		Form semform = userAgent.doc.getForm("<form name=semform>");

		semform.submit();

		userAgent.doc.getForm("<form name=ladenform>").setHidden("sessionid", sessionId);
		userAgent.doc.getForm("<form name=ladenform>").submit();


		userAgent.doc.getForm("<form name=continueform>").submit();

		Util.waitSeconds(2);
		

		return userAgent.getSource();
	}



}
