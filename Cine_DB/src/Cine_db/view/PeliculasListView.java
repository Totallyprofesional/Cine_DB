
package Cine_db.view;
 
import Cine_db.db.model.Peliculas;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PeliculasListView extends JPanel {
    private final DefaultTableModel model = new DefaultTableModel(
        new Object[]{"Id", "Titulo", "Director", "Genero", "Anio", "Duracion"}, 0) {

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private final JTable table = new JTable(model);
    private final JButton btnRefrescar = new JButton("Refrescar");

    public PeliculasListView() {  

        setLayout(new BorderLayout(8, 8));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(btnRefrescar);
        add(top, BorderLayout.NORTH);
    }

    public void setData(List<Peliculas> peliculas) {

        model.setRowCount(0);

        for (Peliculas p : peliculas) {
 
            model.addRow(new Object[]{
                p.getId(), p.getTitulo(), p.getDirector(), p.getGenero(), p.getAnio(), p.getDuracion()
            });
        }
    }

    public Integer getSelectedId() {

        int row = table.getSelectedRow();

        if (row == -1) {

            return null;
        }

        Object val = model.getValueAt(row, 0);
        return (val == null) ? null : Integer.parseInt(val.toString());
    }

    public JTable getTable() {

        return table; 
    }

    public JButton getBtnRefrescar() {

        return btnRefrescar;
    }
}
