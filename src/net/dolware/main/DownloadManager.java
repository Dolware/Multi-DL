package net.dolware.main;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.net.*;

import net.dolware.utils.Config;
import net.dolware.utils.Logging;
import net.dolware.utils.OSSettings;

public class DownloadManager {
	
	private static DownloadManager CLASS_INSTANCE = new DownloadManager();
	
	public static DownloadManager GetInstance()
	{
		return CLASS_INSTANCE;
	}
	
	private ArrayList<String> InputLines = new ArrayList<String>();
	
	public void ReadInputFile()
	{
		if(Config.GetInstance().GetInputList() != null)
		{
			if(Config.GetInstance().GetInputList().exists())
			{
				try{
					BufferedReader b = new BufferedReader(new FileReader(Config.GetInstance().GetInputList()));
					for(String s; (s = b.readLine()) != null; ){
						this.InputLines.add(s);
					}
					
					this.InitDownload();
				}catch (Exception e){
					e.printStackTrace();
				}
				System.out.println(" ");
				Logging.PrintToConsole("[SUCCESS]", "Finished downloading all files!");
				System.out.println(" ");
			}
		}
	}
	
	public void InitDownload()
	{
		if(this.InputLines.size() != 0)
		{
			try{
				if(Config.GetInstance().SettingType == OSSettings.WINDOWS)
				{
					for(String s : this.InputLines)
					{
						if(s.contains("/"))
						{
							String[] dirs = s.split("/");
							Logging.PrintToConsole("[INFO]", "Downloading: " + dirs[dirs.length - 1].toString());
							URL dl = new URL(s);
							URLConnection c = dl.openConnection();
							InputStream i = c.getInputStream();
							FileOutputStream o = new FileOutputStream(System.getProperty("user.dir") + "/OUTPUT/" + dirs[dirs.length - 1].toString());
							byte[] b = new byte[1024];
							int count;
							while((count = i.read(b)) >= 0){
								o.write(b, 0, count);
							}
							o.flush();
							o.close();
							i.close();
						}
						/*URL dl = new URL(s);
						URLConnection c = dl.openConnection();
						InputStream i = c.getInputStream();
						FileOutputStream o = new FileOutputStream(System.getProperty("user.dir") + "/OUTPUT/" + )*/
					}
				}else if(Config.GetInstance().SettingType == OSSettings.LINUX)
				{
					for(String s : this.InputLines)
					{
						if(s.contains("/"))
						{
							String[] dirs = s.split("/");
							Logging.PrintToConsole("[INFO]", "Downloading: " + dirs[dirs.length - 1].toString());
							URL dl = new URL(s);
							URLConnection c = dl.openConnection();
							InputStream i = c.getInputStream();
							FileOutputStream o = new FileOutputStream(System.getProperty("user.dir") + "/OUTPUT/" + dirs[dirs.length - 1].toString());
							byte[] b = new byte[1024];
							int count;
							while((count = i.read(b)) >= 0){
								o.write(b, 0, count);
							}
							o.flush();
							o.close();
							i.close();
						}
					}
				}
			}catch (Exception e){
				
			}
		}else{
			Logging.PrintToConsole("[ERROR]", "Invalid size in: DownloadManager.InputLines");
		}
	}
}
