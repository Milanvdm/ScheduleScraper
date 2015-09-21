package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

	public static String getScheduleUrl(String urlWithJs) {
		String link = null;

		String regex = "\\(?\\b((https?://|www[.]|ftp://)|www[.])[-A-Za-z0-9+&amp;@#/%?=~_()|!:,.;]*[-A-Za-z0-9+&amp;@#/%=~_()|]";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(urlWithJs);
		if(m.find()) {
			String urlStr = m.group();
			
			if (urlStr.startsWith("(") && urlStr.endsWith(")")) {
				urlStr = urlStr.substring(1, urlStr.length() - 1);
			}
			link = urlStr;
		}
		return link;
	}

	

}
