package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitHelper;

import java.time.Duration;
import java.util.List;

public class SearchCustomerPage 
{
    public WebDriver driver;
    WaitHelper waitHelper;

    public SearchCustomerPage(WebDriver rdriver) 
    {
        driver = rdriver;
        PageFactory.initElements(driver, this);
        waitHelper = new WaitHelper(driver);
    }

    @FindBy(how = How.ID, using = "SearchEmail")
    @CacheLookup
    WebElement textEmail;

    @FindBy(how = How.ID, using = "SearchFirstName")
    @CacheLookup
    WebElement textFirstName;

    @FindBy(how = How.ID, using = "SearchLastName")
    @CacheLookup
    WebElement textLastName;

    @FindBy(how = How.ID, using = "search-customers")
    @CacheLookup
    WebElement btnSearch;

    @FindBy(how = How.XPATH, using = "//table[@role='grid']")
    @CacheLookup
    WebElement tableSearchResults;

    @FindBy(how = How.XPATH, using = "//table[@id='customers-grid']")
    WebElement table;

    @FindBy(how = How.XPATH, using = "//table[@id='customers-grid']//tbody/tr")
    List<WebElement> tableRows;

    @FindBy(how = How.XPATH, using = "//table[@id='customers-grid']//tbody/tr/td")
    List<WebElement> tableColumns;

    public void setEmail(String email) 
    {
    	waitHelper.waitForVisibility(textEmail, Duration.ofSeconds(15));
        textEmail.clear();
        textEmail.sendKeys(email);
    }

    public void setFirstName(String fname) 
    {
    	waitHelper.waitForVisibility(textFirstName, Duration.ofSeconds(15));
        textFirstName.clear();
        textFirstName.sendKeys(fname);
    }

    public void setLastName(String lname) 
    {
    	waitHelper.waitForVisibility(textLastName, Duration.ofSeconds(15));
        textLastName.clear();
        textLastName.sendKeys(lname);
    }

    public void clickSearch() 
    {
    	waitHelper.waitForVisibility(btnSearch, Duration.ofSeconds(15));
        btnSearch.click();
    }

    public int getNoOfRows() 
    {
        return (tableRows.size());
    }

    public int getNoOfColumns() 
    {
        return (tableColumns.size());
    }

    public boolean searchCustomerByEmail(String email) 
    {
        boolean flag = false;

        for (int i = 1; i <= getNoOfRows(); i++) 
        {
            String emailid = table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr[" + i + "]/td[2]")).getText();

            System.out.println(emailid);

            if (emailid.equals(email)) 
            {
                flag = true;
                break;
            }
        }
        
        return flag;
    }

    public boolean searchCustomerByName(String Name) 
    {
        boolean flag = false;

        for (int i = 1; i <= getNoOfRows(); i++) 
        {
            String name = table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr[" + i + "]/td[3]")).getText();

            if (Name.equals(name)) 
            {
                flag = true;
                break;
            }
        }

        return flag;
    }
}
