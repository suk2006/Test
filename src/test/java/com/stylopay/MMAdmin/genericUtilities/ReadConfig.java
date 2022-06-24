package com.stylopay.MMAdmin.genericUtilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {
	
	Properties properties;
	
	public ReadConfig(){
		
		File src = new File("./Configuration/config.properties");
		try{
			
			FileInputStream inputstream = new FileInputStream(src);
			properties = new Properties();
			properties.load(inputstream);
		}catch(Exception e){
			
			e.printStackTrace();
		}		
		
	}
	
	public String getApplicationURL(){
		
		String url = properties.getProperty("baseURL");
		return url;		
	}
	
	public String getLoginUserName(){
		
		String loginUserName = properties.getProperty("loginUserName");
		return loginUserName;		
	}	
	
	public String getLoginPassword(){
		
		String loginPassword = properties.getProperty("loginPassword");
		return loginPassword;		
	}

	public String getChromepath(){
	
		String chromepath = properties.getProperty("chromepath");
		return chromepath;	
	}

	public String getFirefoxpath(){
	
		String firefoxpath = properties.getProperty("firefoxpath");
		return firefoxpath;	
	}
	
	public String getEdgepath(){
		
		String edgepath = properties.getProperty("edgepath");
		return edgepath;	
	}

}
