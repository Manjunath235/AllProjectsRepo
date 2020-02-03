package stepDefinition;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

import com.vimalselvam.cucumber.listener.Reporter;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.windows.WindowsDriver;

import utilities.AppIds;
import utilities.Helpers;
import utilities.ScreenShot;
import utilities.SharedDriver;

public class WindowStepDefinition {

	private WindowsDriver<WebElement> driver;
	
	//private static WindowsDriver<WebElement> driver=null;
	private static WebElement calResult=null;
	static String browser="Chrome";
	AppIds app=new AppIds();
	ScreenShot screenshot;
	Helpers help=new Helpers();
	SharedDriver shdriver;
	List<WebElement> lii;
	static String scenarioName;

	String image,message,methodName;
	String resultsPath,srcFolderName;
	
	@Before
	public void setup(Scenario scenario) throws IOException
	{
		
		System.out.println("Before scenario------------------excecuted");
		//	System.out.println("------------------excecuted");
			shdriver = new SharedDriver(browser);
			driver = shdriver.getDriver();
			scenarioName=scenario.getName();
		//calResult=driver.findElementByAccessibilityId("CalculatorResults");
		//screenshot.screenCapture(driver);
		
		screenshot=new ScreenShot();
		//screenshot.screenCapture();
		System.out.println(calResult);
		//Assert.assertNotNull(calResult);
	}
	
	@Given("calculator open using winappdriver")
	public void calculator_open_using_winappdriver() throws IOException, Exception {
		calResult=driver.findElementByAccessibilityId("CalculatorResults");
		Reporter.addScreenCaptureFromPath(screenshot.capture(driver, scenarioName));
		driver.findElementByName("Clear").click();
	}

	@When("i perform addition action on calculator")
	public void i_perform_addition_action_on_calculator() throws IOException, Exception {
		driver.findElementByName("Five").click();
		driver.findElementByName("Plus").click();
		driver.findElementByName("Four").click();
		driver.findElementByName("Equals").click();

	}

	@Then("I validate the addition outcome")
	public void i_validate_the_addition_outcome() throws IOException, Exception {
		Reporter.addScreenCaptureFromPath(screenshot.capture(driver, scenarioName));
		Assert.assertEquals("9", getCalculatorResult());
	}

	@Given("i click on left top button on calculator")
	public void i_click_on_left_top_button_on_calculator() throws IOException, Exception {
	    
		driver.findElementByAccessibilityId("TogglePaneButton").click();
		Reporter.addScreenCaptureFromPath(screenshot.capture(driver, scenarioName));
	}

	@When("i navigate to length section and navigate back to main page")
	public void i_navigate_to_length_section_and_navigate_back_to_main_page() throws IOException, Exception {
		List<WebElement> li=driver.findElementsByXPath("//List");
		System.out.println("list size "+li.size());
		System.out.println(li);
		for(WebElement we:li)
		{
			System.out.println(we.getText().toString());
		}
		
		List<WebElement> lii=driver.findElementsByXPath("//Window[@AutomationId='PaneRoot']//List/ListItem[@ClassName='ListViewItem']");
		
		System.out.println("list size "+lii.size());
		System.out.println(lii);
		for(WebElement we:lii)
		{
			//System.out.println(we);
			System.out.println(we.getText().toString());
			if(we.getText().toString().contains("Length"))
				
			{
				we.click();
				break;
			}
		}
		Reporter.addScreenCaptureFromPath(screenshot.capture(driver, scenarioName));
	}

	@Then("verify the home page standard")
	public void verify_the_home_page_standard() throws IOException, Exception {
	  
		driver.findElementByAccessibilityId("TogglePaneButton").click();
		Reporter.addScreenCaptureFromPath(screenshot.capture(driver, scenarioName));
		lii=driver.findElementsByXPath("//Window[@AutomationId='PaneRoot']//List/ListItem[@ClassName='ListViewItem']");
		for(WebElement we:lii)
		{
			//System.out.println(we);
			System.out.println(we.getText().toString());
			if(we.getText().toString().contains("Standard"))
			{
				we.click();
				break;
			}
		}
		
		//driver.findElementByName("Standard Calculator").click();
		Reporter.addScreenCaptureFromPath(screenshot.capture(driver, scenarioName));
		
		
		List<WebElement> lit=driver.findElementsByXPath("//Group[@Name='Number pad']/Button");
		System.out.println("list size "+lit.size());
		System.out.println(lit);
		for(WebElement we:lit)
		{
			System.out.println(we.getText().toString());
		}
		
	}
	
	@AfterClass
	public void TearDown() throws InterruptedException {
		Thread.sleep(2000);
		calResult=null;
		if(driver!=null)
		{
			driver.quit();
		}
		driver=null;
	}
	
	protected String getCalculatorResult()
	{
		String result=calResult.getText().replace("Display is", "").trim();
		return result;
	}
}
