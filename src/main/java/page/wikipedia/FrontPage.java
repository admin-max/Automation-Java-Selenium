package page.wikipedia;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import page.AbstractPage;
import util.Constants;
import util.UrlProvider;

public class FrontPage extends AbstractPage {

	public FrontPage() {
		super();
		url = UrlProvider.getPageURL(Constants.WIKIPEDIA_FRONT_PAGE);
	}

	@Override
	protected void verifyPage() {
		By WikipediaLogo = new By.ByCssSelector("img.central-featured-logo");

		
		LOGGER.info("Validating the page was loaded");
		wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(WikipediaLogo));
	}

	// Verify language of search is English
	public void selectLanguageOfSearch(String language) throws InterruptedException {
		By languageSelect = new By.ByCssSelector("#searchLanguage");

		LOGGER.info("Selecting search language: " + language);
		WebElement weSelect = driver.findElement(languageSelect);
		Select select = new Select(weSelect);
		select.selectByVisibleText(language);
	}

	public ArticlePage searchQuery(String searchQuery) {

		By searchBar = new By.ByCssSelector("input#searchInput");

		LOGGER.info("Locating search bar and typing query " + "\"" + searchQuery + "\"");

		WebElement weSearchBar = driver.findElement(searchBar);
		weSearchBar.sendKeys(searchQuery + Keys.ENTER);

		ArticlePage articlePage = new ArticlePage();
		articlePage.verifyPage();

		return articlePage;
	}

}
