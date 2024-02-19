package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Steps extends BaseClass 
{
	@Before
	public void setup() throws IOException 
	{
		// Logger configuration
		logger = Logger.getLogger("nopcommerce");
		PropertyConfigurator.configure("Log4j.properties");

		// Loading Config.properties file 
		configProp = new Properties();
		FileInputStream configPropfile = new FileInputStream("config.properties");
		configProp.load(configPropfile);

		// Browser configuration
		String browser = configProp.getProperty("browser");

		if (browser.equals("chrome")) 
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if (browser.equals("firefox")) 
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if (browser.equals("edge")) 
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
	}

	@Given("User Launch Chrome browser")
	public void user_Launch_Chrome_browser() 
	{
		logger.info("********* Launching browser *********");
		loginPage = new LoginPage(driver);
	}

	@When("User opens URL {string}")
	public void user_opens_URL(String url) throws InterruptedException 
	{
		logger.info("********* Opening URL *********");
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(1000);
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_Email_as_and_Password_as(String email, String password) throws InterruptedException 
	{
		logger.info("********* Proving user info *********");
		loginPage.setUserName(email);
		loginPage.setPassword(password);
		Thread.sleep(1000);
	}

	@When("Click on Login")
	public void click_on_Login() throws InterruptedException 
	{
		loginPage.clickLogin();
		Thread.sleep(1000);
	}

	@Then("Page Title should be {string}")
	public void page_Title_should_be(String exptitle) throws InterruptedException 
	{
		logger.info("********* Login Validation starts *********");
		if (driver.getPageSource().contains("Login was unsuccessful")) 
		{
			logger.info("********* Login Failed *********");
			driver.quit();
			Assert.assertTrue(false);
		} 
		else 
		{
			logger.info("********* Login successfull *********");
			Assert.assertEquals(exptitle, driver.getTitle());
		}
		Thread.sleep(1000);
	}

	@When("User click on Log out link")
	public void user_click_on_Log_out_link() throws InterruptedException 
	{
		logger.info("********* Logout from application *********");
		loginPage.clickLogout();
		Thread.sleep(1000);
	}

	@Then("close browser")
	public void close_browser() 
	{
		logger.info("********* Closing application *********");
		driver.quit();
		// Not using driver.close() method to avoid the connection reset error.
	}

	// Customer feature step definitions..........................................
	// Adding Customer

	@Then("User can view Dashboad")
	public void user_can_view_Dashboad() throws InterruptedException 
	{
		logger.info("********* Adding Customer Scenario started *********");
		addCustomerPage = new AddCustomerPage(driver);
		logger.info("********* Dashboard Display validation *********");
		Assert.assertEquals("Dashboard / nopCommerce administration", addCustomerPage.getPageTitle());
		Thread.sleep(1000);
	}

	@When("User click on customers Menu")
	public void user_click_on_customers_Menu() throws InterruptedException 
	{
		addCustomerPage.clickOnCustomersMenu();
		Thread.sleep(1000);
	}

	@When("click on customers Menu Item")
	public void click_on_customers_Menu_Item() throws InterruptedException 
	{
		addCustomerPage.clickOnCustomersMenuItem();
		Thread.sleep(1000);
	}

	@When("click on Add new button")
	public void click_on_Add_new_button() throws InterruptedException 
	{
		addCustomerPage.clickOnAddnew();
		Thread.sleep(1000);
	}

	@Then("User can view Add new customer page")
	public void user_can_view_Add_new_customer_page() throws InterruptedException 
	{
		Assert.assertEquals("Add a new customer / nopCommerce administration", addCustomerPage.getPageTitle());
		Thread.sleep(1000);
	}

	@When("User enter customer info")
	public void user_enter_customer_info() throws InterruptedException 
	{
		logger.info("********* Providing Customer details *********");
		String email = randomestring() + "@gmail.com";
		addCustomerPage.setEmail(email);
		addCustomerPage.setPassword("test123");
		// Registered - default
		// The customer cannot be in both 'Guests' and 'Registered' customer roles
		// Add the customer to 'Guests' or 'Registered' customer role
		addCustomerPage.setCustomerRoles("Guest");
		Thread.sleep(3000);

		addCustomerPage.setManagerOfVendor("Vendor 2");
		addCustomerPage.setGender("Male");
		addCustomerPage.setFirstName("Ram");
		addCustomerPage.setLastName("Kumar");
		addCustomerPage.setDob("07/06/1995"); // Format: D/MM/YYY
		addCustomerPage.setCompanyName("QA Testing");
		addCustomerPage.setAdminContent("This is for testing.........");
	}

	@When("click on Save button")
	public void click_on_Save_button() throws InterruptedException 
	{
		addCustomerPage.clickOnSave();
		Thread.sleep(1000);
	}

	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String string) throws InterruptedException 
	{
		logger.info("********* Add customer validation *********");
		Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
				.contains("The new customer has been added successfully"));
		Thread.sleep(1000);
	}

	// Searching customers using EMail ID ...................................

	@When("Enter customer EMail")
	public void enter_customer_EMail() throws InterruptedException 
	{
		logger.info("********* Search Customer by Email ID Scenario started *********");
		searchCustomerPage = new SearchCustomerPage(driver);
		searchCustomerPage.setEmail("victoria_victoria@nopCommerce.com");
		Thread.sleep(1000);
	}

	@When("Click on search button")
	public void click_on_search_button() throws InterruptedException 
	{
		searchCustomerPage.clickSearch();
		Thread.sleep(3000);
	}

	@Then("User should found Email in the Search table")
	public void user_should_found_Email_in_the_Search_table() throws InterruptedException 
	{
		logger.info("********* Search customer by email validation *********");
		boolean status = searchCustomerPage.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
		Assert.assertEquals(true, status);
		Thread.sleep(1000);
	}

	// Searching customers using Name ...................................
	@When("Enter customer FirstName")
	public void enter_customer_FirstName() throws InterruptedException 
	{
		logger.info("********* Seqarch custoemr by Name Scenario started *********");
		searchCustomerPage = new SearchCustomerPage(driver);
		searchCustomerPage.setFirstName("Victoria");
		Thread.sleep(1000);
	}

	@When("Enter customer LastName")
	public void enter_customer_LastName() throws InterruptedException 
	{
		logger.info("********* Providing customer name *********");
		searchCustomerPage.setLastName("Terces");
		Thread.sleep(1000);
	}

	@Then("User should found Name in the Search table")
	public void user_should_found_Name_in_the_Search_table() throws InterruptedException 
	{
		logger.info("********* Search customer by name validation *********");
		boolean status = searchCustomerPage.searchCustomerByName("Victoria Terces");
		Assert.assertEquals(true, status);
		Thread.sleep(1000);
	}
}
