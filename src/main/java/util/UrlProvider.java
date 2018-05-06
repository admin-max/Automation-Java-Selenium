package util;

import java.util.Properties;

public class UrlProvider {
	private static final String PROPERTIES_FILE_NAME = "pageUrls.properties";
	private static Properties props = null;

	static {
		props = PropertiesLoader.loadfile(PROPERTIES_FILE_NAME);
	}

	public static String getPageURL(String property) {
		String value = null;
		value = props.getProperty(property);

		return value;
	}
}
