package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeClass {

	
	public String currentTime() {
		
		SimpleDateFormat sdf=new SimpleDateFormat("dd_mm_yyyy_hhmmss");
		Date date=new Date();
		String output=sdf.format(date);
		System.out.println(output);
		return output;
	}
	
	
}
