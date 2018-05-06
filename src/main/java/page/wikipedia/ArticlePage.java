package page.wikipedia;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import page.AbstractPage;

public class ArticlePage extends AbstractPage {

	By WikipediaArticleLogo = new By.ByCssSelector("a.mw-wiki-logo");
	By periodicTableLocator = By.cssSelector(".mw-collapsible-content");
	By rowLocator = By.cssSelector("tr");
	By cellLocator = By.cssSelector("span.nounderlines");
	By elementWeightLocator = By.cssSelector(".nounderlines> a > span > span:nth-child(2)");
	By elementNameLocator = By.cssSelector("a > span > span:nth-child(1)");

	public void verifyPage() {
		LOGGER.info("Validating the page was loaded");
		wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(WikipediaArticleLogo));
	}

	// Verify the Periodic Table has 118 elements
	public void verifyTotalNumberOfElements(int expectedNumberOfElements) {
		LOGGER.info("Verifying the Periodic Table has " + expectedNumberOfElements + " elements");

		List<WebElement> wElements = driver.findElements(cellLocator);

		if (expectedNumberOfElements == wElements.size()) {
			LOGGER.info("YES, the Periodic Table has " + wElements.size() + " elements");
		} else {
			LOGGER.fatal("NO, the Periodic Table does not have " + expectedNumberOfElements + " elements. It has "
					+ wElements.size() + " elements.");
		}
		
	}

	// Print all elements atomic weight and name
	public void printAllElementsAtomicWeightAndName() {
		LOGGER.info("Printing all elements atomic weight and name");

		List<WebElement> wElementWeights = driver.findElements(elementWeightLocator);
		Iterator<WebElement> i = wElementWeights.iterator();

		List<WebElement> wElementNames = driver.findElements(elementNameLocator);
		Iterator<WebElement> j = wElementNames.iterator();

		while (i.hasNext()) {
			WebElement weElementWeight = i.next();
			WebElement weElementName = j.next();
			
			
			LOGGER.info(weElementWeight.getAttribute("textContent") + " - "	+ weElementName.getAttribute("textContent").replaceAll("[­]", ""));
			
		}
		LOGGER.info("====================================");
	}

	// Assert the sum of all elements weight is 7021
	public void assertWeightTotal(int expectedTotalWeight) {
		LOGGER.info("Asserting the sum of all elements weight is " + expectedTotalWeight);

		List<WebElement> wElementWeights = driver.findElements(elementWeightLocator);
		Iterator<WebElement> i = wElementWeights.iterator();

		Integer sum = 0;
		while (i.hasNext()) {
			WebElement weElementWeight = i.next();
			int w = Integer.parseInt(weElementWeight.getText());
			sum = sum + w;
		}
		if (sum == expectedTotalWeight) {
			LOGGER.info("YES, the sum of all elements weight is " + sum);
		} else {
			LOGGER.fatal("NO, the sum of all elements weight is " + sum + " instead of " + expectedTotalWeight);
		}
	}

	// Assert the name of the element with atomic weight of 34 is Selenium
	public void assertCertainElement(String requestedElementWeight, String expectedElementName) {
		LOGGER.info("Asserting the name of the element with atomic weight of " + requestedElementWeight + " is "
				+ expectedElementName);

		List<WebElement> wElementCells = driver.findElements(cellLocator);
		Iterator<WebElement> c = wElementCells.iterator();

		while (c.hasNext()) {
			WebElement weElementCell = c.next();
			if (weElementCell.getText().contains(requestedElementWeight)) {
				
				if (weElementCell.getText().replaceAll("[­]", "").contains(expectedElementName)) {
					LOGGER.info("YES, the element with weight " + requestedElementWeight + " found and its name is "
							+ expectedElementName + " as expected");
				} else {
					LOGGER.fatal("Element with weight " + requestedElementWeight + " found. But its name IS NOT "
							+ expectedElementName + " as expected"); 
					
				}
			}
		}
	}

	// Print out the elements of the first 7 rows in the console per row!
	public void printElementsPerRow(int numberOfRowsToPrint) {
		int numberOfFirstRowContainingElements = 2;

		LOGGER.info("Printing elements of the first 7 rows in the console per row");

		
		WebElement wePeriodicTable = driver.findElement(periodicTableLocator);
		List<WebElement> wRows = wePeriodicTable.findElements(rowLocator);
		
		int rowIndex = 1;

		for (WebElement row : wRows.subList(numberOfFirstRowContainingElements,
				numberOfFirstRowContainingElements + numberOfRowsToPrint)) {
			LOGGER.info("====================================");
			LOGGER.info("Row " + rowIndex);
			LOGGER.info("====================================");

			List<WebElement> wCells = row.findElements(cellLocator);
			Iterator<WebElement> c = wCells.iterator();

			while (c.hasNext()) {

				WebElement weCell = c.next();
				
				
				WebElement weElementWeight = weCell.findElement(By.cssSelector("span:nth-child(2)"));
				WebElement weElementName = weCell.findElement(elementNameLocator);
				
		//		String elementName = weElementName.getAttribute("textContent").split("[0-9]")[0].replaceAll("[^A-Za-z]", "");

				
				LOGGER.info(weElementWeight.getAttribute("textContent") + " - " + weElementName.getAttribute("textContent").replaceAll("[^A-Za-z]", ""));
				
		//		LOGGER.info(weElementWeight.getAttribute("textContent") + " - " + elementName);

			}
			rowIndex++;
		}
		LOGGER.info("====================================");
	}
}
