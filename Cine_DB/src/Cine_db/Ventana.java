
package Cine_db;
  
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;
        
public class Ventana { 
      
    public static void main(String[] args) {  

        JFrame ventana = new JFrame("Cartelera de Cine");
        JButton agregar = new JButton("Agregar");
        
        JToolBar barra = new JToolBar("Barra de herramientas");
        barra.setFloatable(false);
 
        // Preguntar diseño de barra
        // Orden de botones
        // Conexion con clase datos
        // Ejemplo
        barra.add(agregar);
        
        ventana.add(barra);
        ventana.setSize(600, 400);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
    }  

} 
  