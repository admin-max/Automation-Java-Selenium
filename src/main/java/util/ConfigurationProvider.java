package util;

import java.util.Properties;

public class ConfigurationProvider {
	private static final String PROPERTIES_FILE_NAME = "parameters.properties";
	private static Properties props = null;

	static {
		props = PropertiesLoader.loadfile(PROPERTIES_FILE_NAME);
	}

	public static String getProperty(String property) {
		String value = null;
		value = props.getProperty(property);

		return value;
	}

	public static boolean getPropertyAsBoolean(String property) {
		boolean result = true;
		String value = getProperty(property);
		result = Boolean.parseBoolean(value);

		return result;
	}

	public static int getPropertyAsInt(String property) {
		int result = 0;
		String value = getProperty(property);
		result = Integer.parseInt(value);
		return result;
	}

	public static String getLocalBrowser() {
		return getProperty(Constants.LOCAL_BROWSER);
	}

	public static boolean getMaximizeWindow() {
		boolean shouldMaximizeWindow = false;

		String maximizeParam = System.getProperty(Constants.LOCAL_WINDOW_MAXIMIZE);

		if (maximizeParam != null) {
			shouldMaximizeWindow = Boolean.parseBoolean(maximizeParam);
		} else {
			shouldMaximizeWindow = getPropertyAsBoolean(Constants.LOCAL_WINDOW_MAXIMIZE);
		}
		return shouldMaximizeWindow;
	}

	public static int getLocalWindowWidth() {
		int windowWidth;

		String windowWidthParam = System.getProperty(Constants.LOCAL_WINDOW_WIDTH);

		if (windowWidthParam != null) {
			windowWidth = Integer.parseInt(windowWidthParam);
		} else {
			windowWidth = getPropertyAsInt(Constants.LOCAL_WINDOW_WIDTH);
		}
		return windowWidth;
	}

	public static int getLocalWindowHeight() {
		int windowHeight;

		String windowHeightParam = System.getProperty(Constants.LOCAL_WINDOW_HEIGHT);

		if (windowHeightParam != null) {
			windowHeight = Integer.parseInt(windowHeightParam);
		} else {
			windowHeight = getPropertyAsInt(Constants.LOCAL_WINDOW_HEIGHT);
		}
		return windowHeight;
	}
}
