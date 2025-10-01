
package Cine_db.db; 

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class DataCon {
 
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASS = "12345";

    public static Connection getConnection() throws SQLException { 
        
        return DriverManager.getConnection(URL, USER, PASS);
    }
    
}
  