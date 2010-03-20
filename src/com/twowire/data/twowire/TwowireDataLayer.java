package com.twowire.data.twowire;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class TwowireDataLayer
{
    private static String tableName = "users";
    private static String issueTable = "issue";
    private static Connection conn = null;
    private static Statement stmt = null;

/*    private void createConnection()
    {
    	try {
	        Context initCtx = new InitialContext();
	        Context envCtx = (Context) initCtx.lookup("java:comp/env");
	        DataSource ds = (DataSource) envCtx.lookup("jdbc/MyDB");
			conn = ds.getConnection();
		} catch (Exception e) {
			System.out.println("Create connection exception");
		}
    }


 * use this for developing in Eclipse
 * make sure to uncomment the line in shutdown() below as well
 */
    private static void createConnection()
    {
        try
        {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            //Get a connection
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/myDB;create=true;user=me;password=mine"); 
        }
        catch (Exception except)
        {
            except.printStackTrace();
        }
    }


    
    public void insertUser(String user, String password)
    {
    	createConnection();
        try
        {
        	String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
            stmt = conn.createStatement();
            stmt.execute("insert into " + tableName + " values ('" +
                    user + "','" + hashed +"')");
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
        shutdown();
    }
    
    public boolean validateUser(String user, String password)
    {
    	createConnection();
    	String candidate = "incorrectPassword";
        try
        {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from " + tableName 
            		+ " where name='" + user + "'");
            if(results.next())
            {
                candidate = results.getString(2);
            }
            results.close();
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
		shutdown();
		return BCrypt.checkpw(password, candidate); 
    }
    
    private void shutdown()
    {
        try
        {
            if (stmt != null)
            {
                stmt.close();
            }
            if (conn != null)
            {
//				Uncomment for Eclispe development
           	    DriverManager.getConnection("jdbc:derby://localhost:1527/myDB;create=true;user=me;password=mine;shutdown=true");
                conn.close();
            }           
        }
        catch (SQLException sqlExcept)
        {
            
        }

    }

	public void addIssue(HashMap result, String organization) {
		createConnection();
        try
        {
            stmt = conn.createStatement();
            stmt.execute("insert into " + issueTable + " values ('" +
                    result.get("reporter") + "','" + result.get("key") + "','" + organization +"')");
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
		shutdown();
	}

	public List<String> getIssueKeysForUser(String user) {                              ////
    	createConnection();
    	List<String> keys = new ArrayList<String>();
        try
        {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select issue from " + issueTable 
            		+ " where name='" + user + "'");
            while(results.next())
            {
            	keys.add(results.getString(1));
            }
            results.close();
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
		shutdown();
		return keys;
	}

	public List<String> getIssueKeysForOrg(String organization) {
    	createConnection();
    	List<String> keys = new ArrayList<String>();
        try
        {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select issue from " + issueTable 
            		+ " where organization='" + organization + "'");
            while(results.next())
            {
            	keys.add(results.getString(1));
            }
            results.close();
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
		shutdown();
		return keys;
	}
}

