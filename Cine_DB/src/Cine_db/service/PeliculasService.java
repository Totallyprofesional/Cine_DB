package Cine_db.service;

import Cine_db.dao.PeliculasDAO;
import Cine_db.database.DataCon;
import Cine_db.database.model.Peliculas;
import java.sql.CallableStatement; 
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.sql.Types; 
import java.util.ArrayList; 
import java.util.List;
 
public class PeliculasService {   
    private PeliculasDAO dao;

    public PeliculasService(PeliculasDAO dao) {
        this.dao = dao;
    } 
    
    public List<Peliculas> listar() throws SQLException {
        List<Peliculas> lista = new ArrayList<>();

        String call = "{ CALL sp_listar_peliculas() }";

        try (Connection con = DataCon.getConnection(); 
            CallableStatement cs = con.prepareCall(call);
            ResultSet rs = cs.executeQuery()) {

            while (rs.next()) {
                Peliculas p = new Peliculas(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("director"),
                    rs.getString("genero"),
                    rs.getInt("anio"),
                    rs.getInt("duracion")
                );
                lista.add(p);
            }
        }
        return lista;
    }

    public int agregar(Peliculas p) throws SQLException {
        String call = "{ CALL sp_insertar_peliculas(?, ?, ?, ?, ?, ?) }";

        try (Connection con = DataCon.getConnection(); 
             CallableStatement cs = con.prepareCall(call)) {

            cs.setString(1, p.getTitulo());
            cs.setString(2, p.getDirector());
            cs.setString(3, p.getGenero());
            cs.setInt(4, p.getAnio());
            cs.setInt(5, p.getDuracion());
            cs.registerOutParameter(6, Types.INTEGER); 

            cs.execute(); 

            return cs.getInt(6);
        } 
    } 

    public int modificar(Peliculas p) throws SQLException {
        String call = "{ CALL sp_modificar_peliculas(?, ?, ?, ?, ?, ?, ?) }";

        try (Connection con = DataCon.getConnection(); 
             CallableStatement cs = con.prepareCall(call)) {

            cs.setInt(1, p.getId());
            cs.setString(2, p.getTitulo());
            cs.setString(3, p.getDirector());
            cs.setString(4, p.getGenero());
            cs.setInt(5, p.getAnio());
            cs.setInt(6, p.getDuracion());
            cs.registerOutParameter(7, Types.INTEGER); 

            cs.execute();

            return cs.getInt(7);
        }
    }

    public String buscar(int id) throws SQLException {
        String call = "{ CALL sp_obtener_titulo_por_id(?, ?) }";

        try (Connection con = DataCon.getConnection(); 
             CallableStatement cs = con.prepareCall(call)) {

            cs.setInt(1, id);
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.execute();

            return cs.getString(2);
        }
    }

    public int eliminar(int id) throws SQLException { 
        String call = "{ CALL sp_eliminar_peliculas(?, ?) }";

        try (Connection con = DataCon.getConnection(); 
             CallableStatement cs = con.prepareCall(call)) {

            cs.setInt(1, id);
            cs.registerOutParameter(2, Types.INTEGER);
            cs.execute();

            return cs.getInt(2);
        }
    }
}