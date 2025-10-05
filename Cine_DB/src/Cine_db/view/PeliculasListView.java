
package Cine_db.view;

import Cine_db.database.model.Peliculas;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*; 
import java.util.List; 

public class PeliculasListView extends JPanel {
    private final DefaultTableModel model;
    private final JTable tabla; 
    private final TableRowSorter<DefaultTableModel> sorter;

    private final JComboBox<String> cbGenero;
    private final JTextField txtAnioMin, txtAnioMax;
    private final JButton btnFiltrar, btnLimpiar, btnOrdenarGenero, btnOrdenarAnio, btnRefrescar;

    public PeliculasListView() {
        setLayout(new BorderLayout(5,5));

        model = new DefaultTableModel(new Object[]{"ID","Título","Director","Género","Año","Duración"}, 0);
        tabla = new JTable(model);
        sorter = new TableRowSorter<>(model);
        tabla.setRowSorter(sorter);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel filtros = new JPanel(new FlowLayout(FlowLayout.LEFT, 5,5));
        cbGenero = new JComboBox<>(new String[]{"(Todos)", "Comedia","Drama","Accion", "Fantasia", "Musical","Romance"});
        txtAnioMin = new JTextField(5);
        txtAnioMax = new JTextField(5); 
        btnFiltrar = new JButton("Filtrar");
        btnLimpiar = new JButton("Limpiar");
        btnRefrescar = new JButton ("Refrescar");
        filtros.add(new JLabel("Género:")); filtros.add(cbGenero);
        filtros.add(new JLabel("Año mín:")); filtros.add(txtAnioMin);
        filtros.add(new JLabel("Año máx:")); filtros.add(txtAnioMax);
        filtros.add(btnFiltrar); filtros.add(btnLimpiar);
        add(filtros, BorderLayout.NORTH);

        JPanel orden = new JPanel(new FlowLayout(FlowLayout.LEFT,5,5));
        btnOrdenarGenero = new JButton("Ordenar por Género");
        btnOrdenarAnio = new JButton("Ordenar por Año");
        orden.add(btnOrdenarGenero); orden.add(btnOrdenarAnio);
        add(orden, BorderLayout.SOUTH);
    }
     
    public void setData(List<Cine_db.database.model.Peliculas> peliculas) {
        model.setRowCount(0);
        for (Cine_db.database.model.Peliculas p : peliculas) {
            model.addRow(new Object[]{
                p.getId(), p.getTitulo(), p.getDirector(),
                p.getGenero(), p.getAnio(), p.getDuracion()  
            }); 
        }
    }
 
    public DefaultTableModel getModel() {
        return model;
    }

    public JTable getTabla() {
        return tabla;
    }
 
    public TableRowSorter<DefaultTableModel> getSorter() {
        return sorter;
    }

    public JComboBox<String> getCbGenero() {
        return cbGenero;
    }

    public JTextField getTxtAnioMin() {
        return txtAnioMin;
    }

    public JTextField getTxtAnioMax() {
        return txtAnioMax;
    }

    public JButton getBtnFiltrar() {
        return btnFiltrar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public JButton getBtnOrdenarGenero() {
        return btnOrdenarGenero;
    }

    public JButton getBtnOrdenarAnio() {
        return btnOrdenarAnio;
    }
    
    public JButton getBtnRefrescar() { 
        return btnRefrescar; 
    }

}