package util;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShot {

	public static void takeScreenShot(WebDriver driver) {	
		
		String fileName =new Object(){}.getClass().getEnclosingMethod().getName();
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File sourse = ts.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(sourse, new File("./Screenshots/"+fileName+".png"));
		} catch (IOException e) {
			System.out.println("Unable to take screenshot "+e.getMessage());
			e.printStackTrace();
		}
		System.out.println("Screeenshot taken");
	}

}