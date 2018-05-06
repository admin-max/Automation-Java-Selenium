package framework;

import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import util.ConfigurationProvider;

public class DriverManager {
	final static Logger LOGGER = Logger.getLogger(DriverManager.class);
	private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<WebDriver>();

	public static WebDriver getDriver() {
		WebDriver driver = driverThreadLocal.get();
		if (driver == null) {
			startDriver();
		}
		driver = driverThreadLocal.get();
		return driver;
	}

	private static void startDriver() {
		String browser = ConfigurationProvider.getLocalBrowser();
		String message = null;
		WebDriver driver;

		LOGGER.info("Starting driver");
		LOGGER.trace(message);
		switch (browser) {
		case "firefox":
			message = "Firefox browser requested";
			driver = new FirefoxDriver();
			break;
		case "chrome":
			message = "Chrome browser requested";
			driver = new ChromeDriver();
			break;
		default:
			message = "Unknown browser requested: " + browser;
			LOGGER.fatal(message);
			throw new RuntimeException(message);
		}
		LOGGER.info(message);

		driverThreadLocal.set(driver);
		setWindowSize();
	}

	private static void setWindowSize() {
		WebDriver driver = driverThreadLocal.get();
		
		boolean shouldMaximazeWindow = ConfigurationProvider.getMaximizeWindow();
		
		if (shouldMaximazeWindow){
			driver.manage().window().maximize();
		}
		else{
			int width = ConfigurationProvider.getLocalWindowWidth();
			int height = ConfigurationProvider.getLocalWindowHeight();
			Dimension targetSize = new Dimension(width, height);
			driver.manage().window().setSize(targetSize);
		}
	}

	public static void closeDriver() {
		WebDriver driver = driverThreadLocal.get();
		driverThreadLocal.set(null);
		if (driver != null) {
			LOGGER.info("Closing the driver");
			driver.close();
			driver = null;
		} else {
			LOGGER.debug("Could not close driver - was null!");
		}
	}
	
	public static void clearCookies(){
		WebDriver driver = driverThreadLocal.get();
		
		driver.manage().deleteAllCookies();

//		Cookie cookie = new Cookie (name, value, path);
//		driver.manage().addCookie(cookie);
	}

}
