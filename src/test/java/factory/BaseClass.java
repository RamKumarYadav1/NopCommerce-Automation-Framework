package factory;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

import java.util.Properties;

public class BaseClass 
{
    public static WebDriver driver;
    public LoginPage loginPage;
    public AddCustomerPage addCustomerPage;
    public SearchCustomerPage searchCustomerPage;
    public static Logger logger;
    Properties configProp;

    //Created for generating random string for Unique email
    public static String randomestring() 
    {
        String generatedString1 = RandomStringUtils.randomAlphabetic(5);
        return (generatedString1);
    }
}
