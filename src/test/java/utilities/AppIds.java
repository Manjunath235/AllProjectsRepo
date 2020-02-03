package utilities;

public class AppIds {

	public String expValue=null;
	
	public String getAppIds(String appids) {
		
		switch (appids) 
		{
		case "calculator":
			
			expValue= "Microsoft.WindowsCalculator_8wekyb3d8bbwe!App";
			break;
			
		case "alarms":
			expValue="Microsoft.WindowsAlarms_8wekyb3d8bbwe!App";
			break;
			
		case "sticky":
			expValue="Microsoft.MicrosoftStickyNotes_8wekyb3d8bbwe!App";
			break;
			
		default:
			System.out.println("None of the appids are matched");
			break;
		}
		return expValue;
	}
	
}
