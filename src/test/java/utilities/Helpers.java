package utilities;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Helpers {

	
	public String createResultsFolder(String FileName) {
//		DateFormat dateformat=new SimpleDateFormat("_dd_mm_yyyy_hh_mm_ss");
//		Calendar cal=Calendar.getInstance();
//		String dt=dateformat.format(cal.getTime());
//		String s=FileName+dt;
//		String FilePath = System.getProperty("user.dir")+"\\ExtentReport";
//		File dir=new File(FilePath);
//		if(!dir.isDirectory())
//		{
//			dir.mkdir();
//		}
//		dir=new File(FilePath+"\\"+s);
//		dir.mkdir();
//		return FilePath+"\\"+s;
		
		DateFormat dateFormat = new SimpleDateFormat("_dd_MM_yyyy_hh_mm_ss");
		Calendar cal = Calendar.getInstance();
		String dt = dateFormat.format(cal.getTime());
		String s = FileName + dt;
		String FilePath=System.getProperty("user.dir")+"\\target\\Test Results";
		File dir=new File(FilePath);
		if(!dir.isDirectory()){
			dir.mkdir();
		}
		
		dir=new File(FilePath+"\\"+s);
		dir.mkdir();
	return FilePath+"\\"+s;
	}
}
