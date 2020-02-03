package utilities;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log {

	static Logger Log = Logger.getLogger(utilities.Log.class);
	static String log4jConfPath = System.getProperty("user.dir")+"\\src\\test\\resources\\Files\\log4j.properties";


	public static void startTestCase(String sTestCaseName){
		PropertyConfigurator.configure(log4jConfPath);
		Log.info("Started Test case Name: "+sTestCaseName);
	}


	public static void endTestCase(String sTestCaseName){
		Log.info("Ended Test Case");
	}

	public static void info(String message)
	{
		PropertyConfigurator.configure(log4jConfPath);
		Log.info(message);
	}
	
	public static void error(String message){
		PropertyConfigurator.configure(log4jConfPath);
		Log.error(message);
	}
	
}
