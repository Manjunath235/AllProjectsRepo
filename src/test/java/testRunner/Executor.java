package testRunner;

import org.junit.runner.RunWith; 
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src//test//resources//Features",
		glue="stepDefinition",	
		monochrome=true,
		plugin = {"com.vimalselvam.cucumber.listener.ExtentCucumberFormatter:output/ExtentReport.html"}
	//	plugin ="com.cucumber.listener.ExtentCucumberFormatter:output/report.html"
		)
public class Executor {

}
