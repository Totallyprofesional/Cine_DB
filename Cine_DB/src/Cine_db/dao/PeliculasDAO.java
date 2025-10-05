
package Cine_db.dao;

import java.sql.*;
import java.util.ArrayList; 
import java.util.List; 
import Cine_db.database.DataCon; 
import Cine_db.database.model.Peliculas;

public class PeliculasDAO { 
    
    public int crear(Peliculas p) throws SQLException {

        String sql = "INSERT INTO peliculas (titulo, director, genero, anio, duracion) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DataCon.getConnection(); PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, p.getTitulo());
            ps.setString(2, p.getDirector());
            ps.setObject(3, p.getAnio(), Types.INTEGER);
            ps.setString(4, p.getGenero());
            ps.setObject(5, p.getDuracion(), Types.INTEGER);

            int filas = ps.executeUpdate();
 
            if (filas == 0) {

                throw new SQLException("La pelicula no se inicia.");
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {

                if (rs.next()) { 

                    int id = rs.getInt(1);
                    p.setId(id);
                    return id;
                }
            }
            throw new SQLException("No se obtuvo ID generado.");
        }
    }

    public Peliculas buscarPorId(int id) throws SQLException {

        String sql = "SELECT id, titulo, director, genero, anio, duracion FROM peliculas WHERE id = ?";

        try (Connection con = DataCon.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) { 

                    return mapear(rs);
                }
            }
        }
        return null;
    }

    public List<Peliculas> listar() throws SQLException {

        String sql = "SELECT id, titulo, director, genero, anio, duracion FROM peliculas ORDER BY id";

        try (Connection con = DataCon.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            List<Peliculas> out = new ArrayList<>();

            while (rs.next()) {

                out.add(mapear(rs));
            }
            return out;
        }
    }

    public int actualizar(Peliculas p) throws SQLException {

        String sql = "UPDATE peliculas SET titulo=?, director=?, genero=?, anio=?, duracion=? WHERE id=?";

        try (Connection con = DataCon.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getTitulo());
            ps.setString(2, p.getDirector());
            ps.setObject(3, p.getAnio(), Types.INTEGER);
            ps.setString(4, p.getGenero());
            ps.setObject(5, p.getDuracion(), Types.INTEGER);
            ps.setInt(6, p.getId());

            return ps.executeUpdate();
        }
    }

    public int eliminar(int id) throws SQLException {

        String sql = "DELETE FROM peliculas WHERE id=?";

        try (Connection con = DataCon.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate();
        }
    }

    public int insertarLoteTransaccional(List<Peliculas> lote) throws SQLException {

        String sql = "INSERT INTO peliculas (titulo, director, genero, anio, duracion) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DataCon.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            con.setAutoCommit(false);
            int total = 0;

            try {

                for (Peliculas p : lote) {

                    ps.setString(1, p.getTitulo()); 
                    ps.setString(2, p.getDirector());
                    ps.setObject(3, p.getAnio(), Types.INTEGER);
                    ps.setString(4, p.getGenero());
                    ps.setObject(5, p.getDuracion(), Types.INTEGER);
                    total += ps.executeUpdate();
                }

                con.commit();  
                return total;

            } catch (SQLException ex) {

                con.rollback();
                throw ex;

            } finally {

                con.setAutoCommit(true);
            }
        }
    }

    private Peliculas mapear(ResultSet rs) throws SQLException {

        return new Peliculas(
                rs.getInt("id"),
                rs.getString("titulo"),
                rs.getString("director"),
                rs.getString("genero"),
                (Integer) rs.getObject("anio"),
                (Integer) rs.getObject("duracion")
        );
    }
}
