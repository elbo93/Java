/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConncet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class DBConnection {
    public static  Connection pmartConnection() throws SQLException{
       Connection con = null;
      
       try
           {
               	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            	con = DriverManager.getConnection("jdbc:sqlserver://DELL-PC\\SQLEXPRESS:2403;databaseName=ResManager","sa","123");
				if(con!=null) 
               		System.out.println("Connection Successful!");
                                else if(con==null) 
                        System.out.println("Connection fail!");                   
           }
           catch(Exception e)
           {
               System.out.println("Error Trace in getConnection() : " + e.getMessage());
           }
        return con;
    }
    
}
