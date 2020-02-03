package windowsAuto;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.BrowserSetUp;
import utilities.Helpers;
import utilities.ScreenShot;



public class SampleTestNG {

	private WebDriver driver;
	//BrowserSetUp browser;
	ScreenShot screenshot;
	ExtentReports report;
	ExtentTest logger;
	Helpers help=new Helpers();
	String image,message,methodName;
	String resultsPath,srcFolderName;

	
	@BeforeClass
	public void setUp() throws IOException
	{
		report=new ExtentReports("C:\\Users\\manjunathgu\\ExtendNew\\Report.html",true);
		//driver=BrowserSetUp.brower("Chrome");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\manjunathgu\\chromedriver\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("window-size=800,600");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		driver=new ChromeDriver(capabilities);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		screenshot=new ScreenShot(driver);
		driver.get("https://www.javatpoint.com/");	
		screenshot.screenshotTake("0", resultsPath);
	}
	
	@Test(priority=1)
	public void checkmethod(Method method) throws InterruptedException, IOException
	{
		logger=report.startTest(method.getName());
		srcFolderName=method.getName();
		resultsPath=help.createResultsFolder(srcFolderName);
		screenshot.screenshotTake("1", resultsPath);
		driver.findElement(By.xpath("//*[@id=\"link\"]/div/ul/li[3]/a")).click();
		Thread.sleep(1000);
		ScreenShot.screenshotTake("2", resultsPath);
		}
	
	
	@AfterMethod
	public void tearExit(ITestResult result) throws IOException
	{

		if(result.getStatus()==ITestResult.FAILURE ){
			System.out.println("method name:" + result.getMethod().getMethodName());
			String image=screenshot.screenshotTake("1", resultsPath);
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
			String image=screenshot.screenshotTake("Pass ", resultsPath);
			logger.log(LogStatus.PASS, "Title verification", logger.addScreenCapture(image));
			report.endTest(logger);
			}
	}
	
	@AfterClass
	public void end()
	{
		driver.quit();
	}
	
}
