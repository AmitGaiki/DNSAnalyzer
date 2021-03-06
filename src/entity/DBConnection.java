package entity;

import java.sql.*;
import java.util.Properties;
import utils.ConfigProperties;


/**
 * This class creates connections to the database by taking the required parameters in constructor.
 * @author amit
 */
public class DBConnection {
	
	private Connection connect;
	private Properties connProperties;
	private ConfigProperties configProperties;
	private String driver;
	private String url;
	private String dbName;
	private String username;
	private String password;
	
	/**
	 * use this constructor to connect using parameters from properties file
	 */
	public DBConnection(){
		this.connect  = null;
		this.configProperties = new ConfigProperties();
		this.driver = configProperties.getDbDriver();
		this.url = configProperties.getDbUrl();
		this.dbName = configProperties.getDbName();
		this.username = configProperties.getUsername();
		this.password = configProperties.getPassword();
	}
	
	/**
	 * use this constructor to provide parameters except username and password
	 * @param dbms dbms name of database system to be used
	 * @param location location to create database file
	 * @param dbname name of database file
	 */
	public DBConnection(String dbms,String location,String dbname){
		this.connect  = null;
		this.configProperties = new ConfigProperties();
		this.driver = configProperties.getDbDriver();
		this.url = "jdbc:" + dbms + ":" + location+ dbname +";create=true";
		this.dbName = dbname;
		this.username = configProperties.getUsername();
		this.password = configProperties.getPassword();
	}
		
	/**
	 * 
	 * @return connection object to be used for all CRUD operations
	 */
	public Connection getConnection(){
		return this.connect;
	}
	
	/**
	 * 
	 * @return name of database file
	 */
	public String getDBName(){
		return this.dbName;
	}
	
	/**
	 * set fields for username, password, database name and database file.
	 */
	public void setProperties(){
		connProperties.setProperty("Username", this.username);
		connProperties.setProperty("Password", this.password);
		connProperties.setProperty("Url", this.url);
		connProperties.setProperty("DBName",this.dbName);
	}
	
	/**
	 * 
	 * @return connection object after making successful connection to database
	 */
	public Connection makeConnection(){
		connect = null;
		connProperties = new Properties();
//		this.setProperties();
		try {
			Class.forName(this.driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connect = DriverManager.getConnection(this.url, this.username, this.password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connect;
	}
	
	/**
	 * creates schema in the database with given name
	 * @param schemaname name of schema to be created in the database
	 * @throws SQLException thrown when creation of schema fails.
	 */
	public void createSchema(String schemaname) throws SQLException{
		Statement stmt = null;
	    try {
	        stmt = this.connect.createStatement();
	        stmt.executeUpdate("CREATE SCHEMA IF NOT EXISTS "+schemaname);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (stmt != null) { stmt.close(); }
	    }
	}
	
	/**
	 * drops the table from database with the given name
	 * @param tableName name of table to drop in database
	 * @throws SQLException thrown when dropping of table fails.
	 */
	public void dropTable(String tableName) throws SQLException{
		Statement stmt = null;
	    try {
	        stmt = this.connect.createStatement();
	        stmt.executeUpdate("DROP TABLE IF EXISTS "+this.dbName+"."+tableName);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (stmt != null) { stmt.close(); }
	    }
	}
	
	/**
	 * creates table in the database with given name
	 * @param tableName name of the table to be created
	 * @param columns columns in the given tablename
	 * @throws SQLException thrown when creation of table fails.
	 */
	public void createTable(String tableName,String columns) throws SQLException {//createString

	    Statement stmt = null;
	    try {
	        stmt = this.connect.createStatement();
	        //stmt.executeUpdate(createString);
	        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS "+this.dbName+"."+tableName+"("+columns+")");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (stmt != null) { stmt.close(); }
	    }
	}
	/**
	 * populate the table with given tablename and attributes
	 * @param tableName name of the table to add record to
	 * @param parameters all attributes for the corresponding columns in table
	 * @throws SQLException thrown when record creation fails.
	 */
	public void populateTable(String tableName,Object...parameters) throws SQLException {
		int i;
	    PreparedStatement pstmt = null;
	    try {
	    	String QMarks = "?";
	    	for(i = 0;i < parameters.length - 1;i++)
	    		QMarks = QMarks + ",?";
	    	String populateTable = "INSERT INTO "+this.dbName+"."+tableName+" VALUES(" +QMarks+ ")";
	        pstmt = this.connect.prepareStatement(populateTable);
	        for(i = 1;i <= parameters.length;i++)
	        	pstmt.setObject(i, parameters[i-1]);
	        pstmt.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (pstmt != null) { pstmt.close(); }
	    }
	}
	
	/**
	 * get resultset object for a particular query executed.
	 * @param tableName name of the table
	 * @param columns name of columns
	 * @param conditions
	 * @return result set object after running the query on database
	 * @throws SQLException thrown when resultset cannot be obtained.
	 */
	public ResultSet retrieveResultSet(String tableName,String columns,String conditions) throws SQLException {

	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try {
	    	String retrieve = "SELECT "+columns+" FROM "+this.dbName+"."+tableName+" WHERE "+conditions;
	        pstmt = this.connect.prepareStatement(retrieve);
	       // for(int i = 1;i <= parameters.length;i++)
	        //	pstmt.setObject(i, parameters[i-1]);
	        rs = pstmt.executeQuery();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (pstmt != null) { pstmt.close(); }
	    }
	    return rs;
	}
}
