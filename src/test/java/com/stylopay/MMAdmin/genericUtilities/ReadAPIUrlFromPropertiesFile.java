package com.stylopay.MMAdmin.genericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadAPIUrlFromPropertiesFile {
	
	static Properties properties;
	
	public static Properties readAPIUrlFromPropertiesFile() throws IOException{
		
		properties = new Properties();
		
		FileInputStream input = new FileInputStream("./apiURLDetails.properties");
		properties.load(input);
		
		return properties;
	}

}
