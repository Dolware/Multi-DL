package net.dolware.main;

import java.io.File;

import net.dolware.utils.Config;
import net.dolware.utils.Logging;
import net.dolware.utils.OSSettings;

public class Main {
	
	public static void main(String[] args)
	{
		System.out.println(" ");
		try {
			Logging.PrintToConsole("[INFO]", "Detecting operating system...");
			Thread.sleep(2000);
			Config.GetInstance().OperatingSystem = System.getProperty("os.name");
			Logging.PrintToConsole("[INFO]", "DETECTED: " + Config.GetInstance().OperatingSystem);
			if(Config.GetInstance().OperatingSystem.contains("Windows"))
			{
				Config.GetInstance().SettingType = OSSettings.WINDOWS;
			}else if(Config.GetInstance().OperatingSystem.contains("Linux"))
			{
				Config.GetInstance().SettingType = OSSettings.LINUX;
			}
			Logging.PrintToConsole("[INFO]", "Using settings for: " + Config.GetInstance().OperatingSystem + "(USING OSSettings." + Config.GetInstance().SettingType + ")");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(args != null)
		{
			for(String arg : args)
			{
				if(arg.startsWith("-")){
					if(arg.contains("="))
					{
						String[] splitter = arg.split("=");
						if(splitter[0].toString().equals("-i") || splitter[0].toString().equals("-input"))
						{
							String cwd = System.getProperty("user.dir");
							File input = new File(cwd + "/" + splitter[1].toString());
							if(input.exists())
							{
								Config.GetInstance().SetInputList(input);
								DownloadManager.GetInstance().ReadInputFile();
							}else{
								Logging.PrintToConsole("[ERROR]", "Input file \"" + splitter[1].toString() + "\" does not exist in the current directory!");
							}
						}
					}
				}
			}
		}
		System.out.println(" ");
	}

}
