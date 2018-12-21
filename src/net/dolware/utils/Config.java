package net.dolware.utils;

import java.io.File;

public class Config {

	private static Config CLASS_INSTANCE = new Config();
	
	public String OperatingSystem = "";
	public OSSettings SettingType;
	
	public static Config GetInstance()
	{
		return CLASS_INSTANCE;
	}
	
	
	private File InputList = null;
	
	
	public void SetInputList(File list)
	{
		if(list != null)
		{
			this.InputList = list;
		}else{
			
		}
	}
	
	public File GetInputList()
	{
		return this.InputList;
	}
}
