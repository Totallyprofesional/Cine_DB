
package Cine_db.controller;

import Cine_db.database.model.Peliculas;
import Cine_db.service.PeliculasService;
import Cine_db.view.PeliculasListView;
import Cine_db.view.PeliculasFormView;
 
import javax.swing.*;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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

        listView.getBtnRefrescar().addActionListener(e -> refrescarTabla());
 
        listView.getBtnFiltrar().addActionListener(e -> aplicarFiltros());
        listView.getBtnLimpiar().addActionListener(e -> {
            listView.getCbGenero().setSelectedIndex(0);
            listView.getTxtAnioMin().setText("");
            listView.getTxtAnioMax().setText("");
            listView.getSorter().setRowFilter(null);
        });

        listView.getBtnOrdenarGenero().addActionListener(e ->
                listView.getSorter().setSortKeys(List.of(
                        new javax.swing.RowSorter.SortKey(3, javax.swing.SortOrder.ASCENDING))));
        listView.getBtnOrdenarAnio().addActionListener(e ->
                listView.getSorter().setSortKeys(List.of(
                        new javax.swing.RowSorter.SortKey(4, javax.swing.SortOrder.ASCENDING))));

        refrescarTabla();
    }

    private void refrescarTabla() {
        try {
            List<Peliculas> data = service.listar();
            listView.setData(data);
        } catch (SQLException ex) {
            formView.showError("Error al listar películas: " + ex.getMessage());
        }
    }

    private void agregarPelicula() {
        try {
            Peliculas p = formView.toModel();
            if (p.getId() != null) {
                formView.showError("El ID debe estar vacío para agregar una película nueva.");
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
                formView.showError("Seleccione una película para eliminar.");
                return;
            }

            int ok = JOptionPane.showConfirmDialog(formView,
                    "¿Eliminar ID " + p.getId() + "?", "Confirmar",
                    JOptionPane.OK_CANCEL_OPTION);

            if (ok == JOptionPane.OK_OPTION) {
                int filas = service.eliminar(p.getId());
                formView.showInfo("Filas eliminadas: " + filas);
                formView.clear();
                refrescarTabla();
            }
        } catch (Exception ex) {
            formView.showError("No se pudo eliminar: " + ex.getMessage());
        }
    }

    private void aplicarFiltros() {
        String genero = (String) listView.getCbGenero().getSelectedItem();
        String minStr = listView.getTxtAnioMin().getText().trim();
        String maxStr = listView.getTxtAnioMax().getText().trim();

        RowFilter<DefaultTableModel, Object> filtroGenero = null;
        if (genero != null && !genero.equals("(Todos)")) {
            filtroGenero = RowFilter.regexFilter("^" + Pattern.quote(genero) + "$", 3);
        }

        RowFilter<DefaultTableModel, Object> filtroAnio = new RowFilter<>() {
            @Override
            public boolean include(Entry<? extends DefaultTableModel, ? extends Object> entry) {
                try {
                    int anio = Integer.parseInt(entry.getStringValue(4));
                    Integer min = minStr.isEmpty() ? null : Integer.parseInt(minStr);
                    Integer max = maxStr.isEmpty() ? null : Integer.parseInt(maxStr);
                    if (min != null && anio < min) return false;
                    if (max != null && anio > max) return false;
                    return true;
                } catch (NumberFormatException ex) {
                    return false;
                }
            }
        };

        List<RowFilter<DefaultTableModel, Object>> filtros = new ArrayList<>();
        if (filtroGenero != null) filtros.add(filtroGenero);
        filtros.add(filtroAnio);

        RowFilter<DefaultTableModel, Object> compuesto =
                filtros.size() == 1 ? filtros.get(0) : RowFilter.andFilter(filtros);

        listView.getSorter().setRowFilter(compuesto);
    }
}