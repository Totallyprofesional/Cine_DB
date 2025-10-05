
package Cine_db; 

import Cine_db.controller.PeliculasController;
import Cine_db.dao.PeliculasDAO;
import Cine_db.service.PeliculasService;
import Cine_db.view.MainWindow; 
import Cine_db.view.PeliculasFormView; 
import Cine_db.view.PeliculasListView; 
import javax.swing.*;

    
public class Launcher { 
 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PeliculasDAO dao = new PeliculasDAO();
            PeliculasService service = new PeliculasService(dao);

            PeliculasListView listView = new PeliculasListView();
            PeliculasFormView formView = new PeliculasFormView();

            MainWindow win = new MainWindow(listView, formView);
            new PeliculasController(service, listView, formView).init();
            win.setVisible(true);
        });
    }
}
 