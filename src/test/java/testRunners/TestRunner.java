package testRunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions
(
		features = ".//Features//Login.feature",
		//features = ".//Features//Customers.feature",
		//features = ".//Features//LoginDataDriven.feature",
        glue = "stepDefinitions",   
        //tags = "@sanity",
        plugin = {"pretty", "html:Test Reports/Latest Test Report"},
        monochrome = true,
        dryRun = false
)
public class TestRunner
{
	
}
