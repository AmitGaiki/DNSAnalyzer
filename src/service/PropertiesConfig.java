/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author amit
 */
public class PropertiesConfig {

	public Properties getPropertiesFile() throws IOException {

		Properties prop = new Properties();
		String filename = "dbconfig.properties";
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filename);
		FileReader fr = new FileReader(filename);
		prop.load(fr);
//		if (inputStream != null) {
//			prop.load(inputStream);
//		} else {
//			throw new FileNotFoundException("file " + filename + " not found");
//		}
		return prop;
	}

	public String getProperty(String key) {
		Properties prop;
		try {
			prop = getPropertiesFile();
			String value = prop.getProperty(key);
			return value;
		} catch (IOException ex) {
			Logger.getLogger(PropertiesConfig.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
}
