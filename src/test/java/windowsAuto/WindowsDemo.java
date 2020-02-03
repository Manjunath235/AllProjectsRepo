package windowsAuto;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.windows.WindowsDriver;
import utilities.AppIds;
import utilities.Helpers;
import utilities.ScreenShot;

public class WindowsDemo{

	
	private static WindowsDriver<WebElement> driver=null;
	private static WebElement calResult=null;
	
	AppIds app=new AppIds();
	ScreenShot screenshot;
	Helpers help=new Helpers();
	
	ExtentReports report;
	ExtentTest logger;
	String image,message,methodName;
	String resultsPath,srcFolderName;
	
	
	@BeforeClass
	public void setUp() throws IOException
	{
		//report=new ExtentReports(System.getProperty("user.dir")+"\\ExtentReport\\ExtentReport.html",false,DisplayOrder.NEWEST_FIRST);
		DesiredCapabilities dcap= new DesiredCapabilities();
		dcap.setCapability("app", app.getAppIds("calculator"));
		dcap.setCapability ("deviceName", "WindowsPC");
		driver=new WindowsDriver<WebElement>(new URL("http://127.0.0.1:4723/"), dcap);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		calResult=driver.findElementByAccessibilityId("CalculatorResults");
		//screenshot.screenCapture(driver);
		report=new ExtentReports("ExtentReport\\newExtend.html",false,DisplayOrder.NEWEST_FIRST);	
		screenshot=new ScreenShot(driver);
		screenshot.screenCapture();
		System.out.println(calResult);
		Assert.assertNotNull(calResult);
		
		
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
	
	@BeforeMethod
	public void clearResult()
	{
		driver.findElementByName("Clear").click();
		Assert.assertEquals("0", getCalculatorResult());
	}
	
	@AfterMethod
	public void tearExit(ITestResult result) throws IOException
	{
//		if(result.getStatus()==ITestResult.FAILURE)
//		{
//			System.out.println("Method Name: "+result.getMethod().getMethodName());
//			image=screenshot.screenCapture();
//			logger.log(LogStatus.FAIL, logger.addScreenCapture(image));
//			message=result.getThrowable().getMessage();
//			logger.log(LogStatus.FAIL, message);
//			report.endTest(logger);
//		}else if(result.getStatus()==ITestResult.SKIP)
//		{
//			System.out.println("Method Name: "+result.getMethod().getMethodName());
//			methodName=result.getMethod().getMethodName();
//			logger=report.startTest(methodName);
//			image=screenshot.screenCapture();
//			logger.log(LogStatus.SKIP, logger.addScreenCapture(image));
//			message=result.getThrowable().getMessage();
//			logger.log(LogStatus.SKIP, message);
//			report.endTest(logger);		
//		}else {
//			System.out.println("Method Name: "+result.getMethod().getMethodName());
//			methodName=result.getMethod().getMethodName();
//			logger=report.startTest(methodName);
//			logger.log(LogStatus.PASS, "Test Method is Passed");		
//			image=screenshot.screenCapture();
//			logger.log(LogStatus.PASS, logger.addScreenCapture(image));
//			report.endTest(logger);		
//		}
		if(result.getStatus()==ITestResult.FAILURE ){
			System.out.println("method name:" + result.getMethod().getMethodName());
			String image=	screenshot.screenshotTake("1", resultsPath);
			logger.log(LogStatus.FAIL,  logger.addScreenCapture(image));
			message = result.getThrowable().getMessage();	 
			logger.log(LogStatus.FAIL, message);
			report.endTest(logger);
		}else if(result.getStatus()==ITestResult.SKIP)	{
			System.out.println("method name:" + result.getMethod().getMethodName());
			String methodName=result.getMethod().getMethodName();
			logger=report.startTest(methodName);
			logger.log(LogStatus.SKIP, "Test Method Skipped");	
			message = result.getThrowable().getMessage();	 
			logger.log(LogStatus.SKIP, message);
			report.endTest(logger);
		}else{
			System.out.println("method name:" + result.getMethod().getMethodName());
			logger.log(LogStatus.PASS, "Test Method Passed");
			logger.log(LogStatus.PASS, "Verify Content Administration is entitled with the user");
			String image=	screenshot.screenshotTake("1", resultsPath);
			logger.log(LogStatus.PASS, "Title verification", logger.addScreenCapture(image));
			report.endTest(logger);
			}
		
	}
	
	protected String getCalculatorResult()
	{
		String result=calResult.getText().replace("Display is", "").trim();
		return result;
	}
	
	@Test(priority=1)
	public void Addition(Method method) throws IOException {
		logger=report.startTest(method.getName());
		srcFolderName=method.getName();
		resultsPath=help.createResultsFolder(srcFolderName);
		screenshot.screenshotTake("01",resultsPath);
		driver.findElementByName("Five").click();
		driver.findElementByName("Plus").click();
		driver.findElementByName("Four").click();
		driver.findElementByName("Equals").click();
		screenshot.screenshotTake("02",resultsPath);
		Assert.assertEquals("9", getCalculatorResult());
	}
	
	@Test(priority=2)
	public void countList(Method method) throws InterruptedException, IOException {
		logger=report.startTest(method.getName());
		srcFolderName=method.getName();
		resultsPath=help.createResultsFolder(srcFolderName);
		driver.findElementByAccessibilityId("TogglePaneButton").click();
		screenshot.screenshotTake("02",resultsPath);
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
		screenshot.screenshotTake("02",resultsPath);
		Thread.sleep(2000);
		driver.findElementByAccessibilityId("TogglePaneButton").click();
		screenshot.screenshotTake("02",resultsPath);
		
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
		screenshot.screenshotTake("02",resultsPath);
		
		
		List<WebElement> lit=driver.findElementsByXPath("//Group[@Name='Number pad']/Button");
		System.out.println("list size "+lit.size());
		System.out.println(lit);
		for(WebElement we:lit)
		{
			System.out.println(we.getText().toString());
		}
		
	}
}
