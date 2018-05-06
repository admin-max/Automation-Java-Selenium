import org.junit.Test;
import framework.FrameworkBase;
import page.wikipedia.ArticlePage;
import page.wikipedia.FrontPage;

public class WikipediaTest extends FrameworkBase {

	String language = "English";
	String searchQuery = "Periodic Table";

	// Test #1
	@Test
	public void verifyTotalElementsAndPrintWeightAndName() throws InterruptedException {
		int expectedNumberOfElements = 118;

		FrontPage frontPage = new FrontPage();
		frontPage.openPage();
		frontPage.selectLanguageOfSearch(language);
		ArticlePage articlePage = frontPage.searchQuery(searchQuery);
		articlePage.verifyTotalNumberOfElements(expectedNumberOfElements);
		articlePage.printAllElementsAtomicWeightAndName();

		Thread.sleep(5000);
	}

	// Test #2
	@Test
	public void assertWeightTotalAndAssertCertainElement() throws InterruptedException {
		int expectedTotalWeight = 7021;
		String requestedElementWeight = "34";
		String expectedElementName = "Selenium";
		
		FrontPage frontPage = new FrontPage();
		frontPage.openPage();
		frontPage.selectLanguageOfSearch(language);
		ArticlePage articlePage = frontPage.searchQuery(searchQuery);
		
		articlePage.assertWeightTotal(expectedTotalWeight);
		
		articlePage.assertCertainElement(requestedElementWeight, expectedElementName);

		Thread.sleep(5000);
	}

	// Test #3
	@Test
	public void printElementsOfFirst7RowsPerRow() throws InterruptedException {
		int numberOfRowsToPrint = 7;

		FrontPage frontPage = new FrontPage();
		frontPage.openPage();
		frontPage.selectLanguageOfSearch(language);
		ArticlePage articlePage = frontPage.searchQuery(searchQuery);
		articlePage.printElementsPerRow(numberOfRowsToPrint);

		Thread.sleep(5000);
	}
}
