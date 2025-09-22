
package magenta; 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
 
public class DatabaseConnection {
 
    public static void main(String[] args) {

        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Cine_DB",
                "root",
                "12345"
        )) {
            System.out.println("Conexión establecida con Cine_DB");
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        } 
    }
    
}
 