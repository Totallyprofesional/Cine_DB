
package Cine_db.view;

import Cine_db.db.model.Peliculas;
import javax.swing.*;
import java.awt.*; 
import java.util.Optional;

public class PeliculasFormView extends JPanel {
    private final JTextField txtId = new JTextField(6);
    private final JTextField txtTitulo = new JTextField(20);
    private final JTextField txtDirector = new JTextField(20);
    private final JTextField txtGenero = new JTextField(6);
    private final JTextField txtAnio = new JTextField(12);
    private final JTextField txtDuracion = new JTextField(6);

    private final JButton btnNuevo = new JButton("Nuevo");
    private final JButton btnAgregar = new JButton("Agregar");
    private final JButton btnModificar = new JButton("Modificar");
    private final JButton btnEliminar = new JButton("Eliminar");
    private final JButton btnLimpiar = new JButton("Limpiar");

    public PeliculasFormView() { 

        setLayout(new BorderLayout(8, 8));

        JPanel form = new JPanel(new GridLayout(0, 2, 6, 6));
        txtId.setEnabled(false);

        form.add(new JLabel("ID:"));
        form.add(txtId);
        form.add(new JLabel("Título:"));
        form.add(txtTitulo);
        form.add(new JLabel("Director:"));
        form.add(txtDirector);
        form.add(new JLabel("Genero:"));
        form.add(txtGenero);
        form.add(new JLabel("Anio:"));
        form.add(txtAnio);
        form.add(new JLabel("Duracion:"));
        form.add(txtDuracion);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botones.add(btnNuevo);
        botones.add(btnAgregar);
        botones.add(btnModificar);
        botones.add(btnEliminar);
        botones.add(btnLimpiar);

        add(form, BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);
    }

    public void setFrom(Peliculas p) {  

        txtId.setText(p.getId() == null ? "" : String.valueOf(p.getId()));
        txtTitulo.setText(Optional.ofNullable(p.getTitulo()).orElse("").trim());
        txtDirector.setText(Optional.ofNullable(p.getDirector()).orElse("").trim());
        txtGenero.setText(Optional.ofNullable(p.getGenero()).orElse("").trim());
        txtAnio.setText(p.getAnio() == null ? "" : String.valueOf(p.getAnio()));
        txtDuracion.setText(p.getDuracion() == null ? "" : String.valueOf(p.getDuracion()));
    }

   public Peliculas toModel() { 

    Integer id = txtId.getText().isBlank() ? null : Integer.parseInt(txtId.getText());
    Integer anio = txtAnio.getText().isBlank() ? null : Integer.parseInt(txtAnio.getText());
    Integer duracion = txtDuracion.getText().isBlank() ? null : Integer.parseInt(txtDuracion.getText());

    Peliculas p = new Peliculas( 
        id, 
        txtTitulo.getText().trim(),
        txtDirector.getText().trim(),
        txtGenero.getText().trim(),
        anio,
        duracion
    );

    return p;
}

    public void clear() {
 
        setFrom(new Peliculas());
    }

    public JButton getBtnNuevo() {

        return btnNuevo;
    }

    public JButton getBtnAgregar() {

        return btnAgregar;
    }

    public JButton getBtnModificar() {

        return btnModificar;
    }

    public JButton getBtnEliminar() {

        return btnEliminar;
    }

    public JButton getBtnLimpiar() {

        return btnLimpiar;
    }

    public void showInfo(String msg) {

        JOptionPane.showMessageDialog(this, msg, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showError(String msg) {

        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
