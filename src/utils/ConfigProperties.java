/*
 * To change this license header, choose License Headers in Project ConfigProperties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author amit
 */
public class ConfigProperties {
	
	private Properties properties;
	private String filename = "dbconfig.properties";
	private String dbDriver;
	private String dbUrl;
	private String dbName;
	private String username;
	private String password;

	public ConfigProperties(){
		FileReader fr = null;
			try {
				this.properties = new Properties();
				fr = new FileReader(filename);
				try {
					properties.load(fr);
					this.dbDriver = properties.getProperty("driver");
					this.dbUrl = properties.getProperty("url");
					this.dbName = properties.getProperty("dbname");
					this.username = properties.getProperty("username");
					this.password = properties.getProperty("password");
				} catch (IOException ex) {
					Logger.getLogger(ConfigProperties.class.getName()).log(Level.SEVERE, null, ex);
				}
			} catch (FileNotFoundException ex) {
				Logger.getLogger(ConfigProperties.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
			try {
				fr.close();
			} catch (IOException ex) {
				Logger.getLogger(ConfigProperties.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getDbDriver() {
		return dbDriver;
	}

	public String getDbUrl() {
		return dbUrl;
	}

	public String getDbName() {
		return dbName;
	}
	
	
}
