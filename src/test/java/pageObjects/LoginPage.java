package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitHelper;

public class LoginPage 
{
    public WebDriver driver;
    WaitHelper waitHelper;
    
    public LoginPage(WebDriver rdriver) 
    {
        driver = rdriver;
        PageFactory.initElements(driver, this);
        waitHelper = new WaitHelper(driver);
    }

    @FindBy(id = "Email")
    @CacheLookup
    WebElement textEmail;

    @FindBy(id = "Password")
    @CacheLookup
    WebElement textPassword;

    @FindBy(xpath = "//button[@type='submit']")
    @CacheLookup
    WebElement btnLogin;

    @FindBy(linkText = "Logout")
    @CacheLookup
    WebElement linkLogout;

    public void setUserName(String username) 
    {
    	waitHelper.waitForVisibility(textEmail, Duration.ofSeconds(15));
        textEmail.clear();
        textEmail.sendKeys(username);
    }

    public void setPassword(String password) 
    {
    	waitHelper.waitForVisibility(textPassword, Duration.ofSeconds(15));
        textPassword.clear();
        textPassword.sendKeys(password);
    }

    public void clickLogin() 
    {
    	waitHelper.waitForVisibility(btnLogin, Duration.ofSeconds(15));
        btnLogin.click();
    }

    public void clickLogout() 
    {
    	waitHelper.waitForVisibility(linkLogout, Duration.ofSeconds(15));
        linkLogout.click();
    }
}
