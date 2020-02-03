package utilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.windows.WindowsDriver;

public class SharedDriver {

	private static WindowsDriver<WebElement> REAL_DRIVER;
	private static boolean initialized = false;
	AppIds app=new AppIds();

	Date date=new Date();
	
    static {
    	  
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try {
				  	REAL_DRIVER.close();
		            REAL_DRIVER.quit();
					Log.info("Driver is closed");
				} catch (Exception e) {
					Log.error("Driver is closed in Exception , Driver Instance :" + REAL_DRIVER + "Ex. Message:"
							+ e.getMessage());
				}
			}
		});

    }

    public SharedDriver(String browser) throws MalformedURLException {

    	if(!initialized){

    		Log.info("*********************Test Excecution Started on "+date+"************************");
    		Log.info("Inside shared driver init.....");
    		
    		DesiredCapabilities dcap= new DesiredCapabilities();
    		dcap.setCapability("app", app.getAppIds("calculator"));
    		dcap.setCapability ("deviceName", "WindowsPC");
    		REAL_DRIVER=new WindowsDriver<WebElement>(new URL("http://127.0.0.1:4723/"), dcap);
    		REAL_DRIVER.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    	
    		//REAL_DRIVER.manage().deleteAllCookies();
        	//REAL_DRIVER.manage().window().maximize();
    		initialized = true;
    		
    	}

    }
    
    public WindowsDriver<WebElement> getDriver(){    	
    	return REAL_DRIVER;
    }
	
}
