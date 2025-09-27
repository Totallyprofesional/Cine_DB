
package Cine_db.view;
import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame { 
 
    private final PeliculasListView listView; 
    private final PeliculasFormView formView;

    public MainWindow(PeliculasListView listView, PeliculasFormView formView) {
        
        super("GamesApp - Gestión de Peliculas");
        this.listView = listView;
        this.formView = formView; 
 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
 
        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                listView, formView);
        split.setResizeWeight(0.6);
        add(split, BorderLayout.CENTER);

        setSize(900, 700);
        setLocationRelativeTo(null);
    }

    public PeliculasListView getListView() {
        
        return listView;
    }

    public PeliculasFormView getFormView() {
        
        return formView;
    }
   
} 
  