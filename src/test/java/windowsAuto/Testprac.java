package windowsAuto;

import java.io.IOException;

public class Testprac {

	public static void main(String[] args) {
		try {
			
			Runtime.getRuntime().exec(new String[] {"cmd", "/K", "Start"});
			
			//Runtime.getRuntime().exec("cmd /c start WinAppDriver.exe /\"C:\\Program Files (x86)\\Windows Application Driver\\WinAppDriver.exe");
			
//		      Runtime runtime = Runtime.getRuntime();
//		      runtime.exec("C:\\Windows\\System32\\cmd.exe");
		    
		   } catch (IOException e1) {
		      e1.printStackTrace();
		   } 

	}

}
