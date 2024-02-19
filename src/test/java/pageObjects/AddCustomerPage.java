package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustomerPage 
{
	public WebDriver driver;

	public AddCustomerPage(WebDriver rdriver) 
	{
		driver = rdriver;
		PageFactory.initElements(driver, this);
	}

	// Page objects
	
	By linkCustomersMenu = By.xpath("//a[@href='#']//p[contains(text(),'Customers')]");
	By linkCustomersMenuItems = By.xpath("//a[@href='/Admin/Customer/List']//p[contains(text(),'Customers')]");
	By btnAddnew = By.xpath("//a[@href='/Admin/Customer/Create']"); // Add new

	By textEmail = By.xpath("//input[@id='Email']");
	By textPassword = By.xpath("//input[@id='Password']");
	By textcustomerRoles = By.xpath("//div[@class='k-multiselect-wrap k-floatwrap']");

	By listItemAdministrators = By.xpath("//li[contains(text(),'Administrators')]");
	By listItem = By.xpath("//li[contains(text(),'Registered')]");
	By lstitemGuests = By.xpath("//li[contains(text(),'Guests')]");
	By lstitemVendors = By.xpath("//li[contains(text(),'Vendors')]");

	By drpmgrOfVendor = By.xpath("//*[@id='VendorId']");
	By rdMaleGender = By.id("Gender_Male");
	By rdFeMaleGender = By.id("Gender_Female");

	By textFirstName = By.xpath("//input[@id='FirstName']");
	By textLastName = By.xpath("//input[@id='LastName']");

	By textDob = By.xpath("//input[@id='DateOfBirth']");
	By textCompanyName = By.xpath("//input[@id='Company']");
	By textAdminContent = By.xpath("//textarea[@id='AdminComment']");
	By btnSave = By.xpath("//button[@name='save']");

	// Actions Methods

	public String getPageTitle() 
	{
		return driver.getTitle();
	}

	public void clickOnCustomersMenu() 
	{
		driver.findElement(linkCustomersMenu).click();
	}

	public void clickOnCustomersMenuItem() 
	{
		driver.findElement(linkCustomersMenuItems).click();
	}

	public void clickOnAddnew() 
	{
		driver.findElement(btnAddnew).click();
	}

	public void setEmail(String email) 
	{
		driver.findElement(textEmail).sendKeys(email);
	}

	public void setPassword(String password) 
	{
		driver.findElement(textPassword).sendKeys(password);
	}

	public void setCustomerRoles(String role) throws InterruptedException 
	{
		if (!role.equals("Vendors")) // If role is vendors should not delete Register as per req.
		{
			driver.findElement(By.xpath("//*[@id=\"SelectedCustomerRoleIds_taglist\"]/li/span[2]")).click();
		}

		driver.findElement(textcustomerRoles).click();

		WebElement listitem;

		Thread.sleep(3000);

		if (role.equals("Administrators")) 
		{
			listitem = driver.findElement(listItemAdministrators);
		} 
		else if (role.equals("Guests")) 
		{
			listitem = driver.findElement(lstitemGuests);
		} 
		else if (role.equals("Registered")) 
		{
			listitem = driver.findElement(listItem);
		} 
		else if (role.equals("Vendors")) 
		{
			listitem = driver.findElement(lstitemVendors);
		} 
		else 
		{
			listitem = driver.findElement(lstitemGuests);
		}

		// listitem.click();
		// Thread.sleep(3000);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", listitem);
	}

	public void setManagerOfVendor(String value) 
	{
		Select drp = new Select(driver.findElement(drpmgrOfVendor));
		drp.selectByVisibleText(value);
	}

	public void setGender(String gender) 
	{
		if (gender.equals("Male")) 
		{
			driver.findElement(rdMaleGender).click();
		} 
		else if (gender.equals("Female")) 
		{
			driver.findElement(rdFeMaleGender).click();
		} 
		else 
		{
			driver.findElement(rdMaleGender).click();// Default
		}
	}

	public void setFirstName(String fname) 
	{
		driver.findElement(textFirstName).sendKeys(fname);
	}

	public void setLastName(String lname) 
	{
		driver.findElement(textLastName).sendKeys(lname);
	}

	public void setDob(String dob) 
	{
		driver.findElement(textDob).sendKeys(dob);
	}

	public void setCompanyName(String comname) 
	{
		driver.findElement(textCompanyName).sendKeys(comname);
	}

	public void setAdminContent(String content) 
	{
		driver.findElement(textAdminContent).sendKeys(content);
	}

	public void clickOnSave() 
	{
		driver.findElement(btnSave).click();
	}
}
