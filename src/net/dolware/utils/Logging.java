package net.dolware.utils;

public class Logging {
	
	public static void PrintToConsole(String prefix, String msg)
	{
		if(prefix == "")
		{
			System.out.println("[INFO]: " + msg);
		}else{
			System.out.println(prefix + ": " + msg);
		}
	}

}
