package page;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.DriverManager;
import util.PropertiesLoader;

public abstract class AbstractPage {
	static protected PropertiesLoader props = new PropertiesLoader();

	protected Logger LOGGER;

	protected String url;
	protected WebDriver driver;
	protected WebDriverWait wdWait;

	protected AbstractPage() {
		LOGGER = Logger.getLogger(this.getClass());
		driver = DriverManager.getDriver();
		wdWait = new WebDriverWait(driver, 15);
	}

	protected abstract void verifyPage();

	public void openPage() {
		if (url == null) {
			String errorMessage = "AbstractPage url is null";
			throw new RuntimeException(errorMessage);
		}
		LOGGER.info("Opening page: " + url);
		driver.get(url);

		LOGGER.info("Calling verifyPage()");
		verifyPage();
	}
}
