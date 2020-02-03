package utilities;

import java.awt.Window;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.appium.java_client.windows.WindowsDriver;

public class ScreenShot {

	String des;
	static WindowsDriver driver;
	//static WebDriver driver;
	TimeClass time=new TimeClass();
	
//	public ScreenShot(@SuppressWarnings("rawtypes") WindowsDriver driver) {
//		this.driver=driver;
//	}
//	public ScreenShot(WindowsDriver driver) {
//		this.driver=driver;
//	}
	
	@SuppressWarnings("rawtypes")
	public String screenCapture() throws IOException {
		//String current=time.currentTime();
		TakesScreenshot takescreen=(TakesScreenshot)driver;
		File fi=takescreen.getScreenshotAs(OutputType.FILE);
		des=System.getProperty("user.dir")+"\\output\\photo_"+time.currentTime()+".png";
		File fo=new File(des);
		FileUtils.copyFile(fi, fo);
		fi.deleteOnExit();
		return des;
	}
	
	public static String screenshotTake(String screenShotName,String folderPath) throws IOException {
		
//		DateFormat dateformat=new SimpleDateFormat("_dd_mm_yyyy_hh_mm_ss");
//		Calendar cal=Calendar.getInstance();
//		String dt=dateformat.format(cal.getTime());
//		System.out.println(dt);
//		//String s=FileName+dt;
//		File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//		FileUtils.copyFile(srcFile, new File(folderPath+"\\"+dt+".png"));
//		return folderPath+"\\"+dt+".png";
		  DateFormat dateFormat = new SimpleDateFormat("_dd_MM_yyyy_hh_mm_ss");
	        Calendar cal = Calendar.getInstance();
	        String dt = dateFormat.format(cal.getTime());
	        String s = screenShotName + dt;

	        try {
	               File scrFile = ((TakesScreenshot)driver)
	                            .getScreenshotAs(OutputType.FILE);
	               FileUtils.copyFile(scrFile, new File(
	                            folderPath+"\\"+s+ ".png"));
	        } catch (Exception e) {
	               e.printStackTrace();
	        }
	        return folderPath+"\\"+s+ ".png";
	}
	
	public  String destination;
	public  String capture(WebDriver driver, String screenshotName) throws Exception {                     
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());      
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);       
		destination = System.getProperty("user.dir") + "\\target\\screenshots\\capture\\"+screenshotName+"\\"+screenshotName+"_"+dateName+".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(scrFile, finalDestination);        
		scrFile.deleteOnExit();
		//System.out.println("The dest : "+destination);
		return destination;
	}
	
}
