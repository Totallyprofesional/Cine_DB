package Cine_db.controller;

import Cine_db.db.model.Peliculas;
import Cine_db.service.PeliculasService;
import Cine_db.view.PeliculasFormView;
import Cine_db.view.PeliculasListView;
import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class PeliculasController {
    
    private final PeliculasService service;
    private final PeliculasListView listView; 
    private final PeliculasFormView formView;

    public PeliculasController(PeliculasService service, PeliculasListView listView, PeliculasFormView formView) {
        this.service = service;
        this.listView = listView;
        this.formView = formView;
    }

    public void init() { 
          
        formView.getBtnAgregar().addActionListener(e -> agregarPelicula());

        formView.getBtnModificar().addActionListener(e -> modificarPelicula());

        formView.getBtnEliminar().addActionListener(e -> eliminarPelicula());

        formView.getBtnLimpiar().addActionListener(e -> formView.clear());

        refrescarTabla();
    }

    private void agregarPelicula() {
        try {
            Peliculas p = formView.toModel();
            if (p.getId() != null) {
                formView.showError("El ID debe estar vacío para agregar una nueva película.");
                return;
            }
            int filas = service.agregar(p);
            formView.showInfo("Filas agregadas: " + filas);
            formView.clear();
            refrescarTabla();
        } catch (Exception ex) {
            formView.showError("No se pudo agregar: " + ex.getMessage());
        }
    }

    private void modificarPelicula() {
        try {
            Peliculas p = formView.toModel();
            if (p.getId() == null) {
                formView.showError("Seleccione una película para modificar.");
                return;
            }
            int filas = service.modificar(p);
            formView.showInfo("Filas modificadas: " + filas);
            formView.clear();
            refrescarTabla();
        } catch (Exception ex) {
            formView.showError("No se pudo modificar: " + ex.getMessage());
        }
    } 

    private void eliminarPelicula() {
        try {
            Peliculas p = formView.toModel();
            if (p.getId() == null) {
                formView.showError("Seleccione un numero de película a eliminar.");
                return;
            }

            int ok = JOptionPane.showConfirmDialog(formView, "¿Eliminar ID " + p.getId() + "?", "Confirmar", JOptionPane.OK_CANCEL_OPTION);
            if (ok == JOptionPane.OK_OPTION) {
                int filas = service.eliminar(p.getId());
                formView.showInfo("Eliminados: " + filas);
                formView.clear();
                refrescarTabla();
            }
        } catch (Exception ex) {
            formView.showError("No se pudo eliminar: " + ex.getMessage());
        }
    }

    private void refrescarTabla() {
        try { 
            List<Peliculas> data = service.listar();
            listView.setData(data);
        } catch (SQLException ex) {
            formView.showError("Error al listar: " + ex.getMessage());
        }
    }
}