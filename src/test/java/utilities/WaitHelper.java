package utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper 
{
	WebDriver driver;
	
	public WaitHelper(WebDriver rdriver)
	{
		this.driver = rdriver;
	}
	
	public void waitForVisibility(WebElement element, Duration timeOutInSeconds)
	{
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	}	
}
