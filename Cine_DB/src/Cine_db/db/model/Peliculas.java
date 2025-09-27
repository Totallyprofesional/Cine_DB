package Cine_db.db.model;
 
public class Peliculas {   
    private Integer id;  
    private String titulo;
    private String director;
    private String genero;
    private Integer anio;
    private Integer duracion;

    public Peliculas() {
    } 
    
    public Peliculas(Integer id, String titulo, String director, String genero, Integer anio, Integer duracion) {
        this.id = id;
        this.titulo = titulo; 
        this.director = director;
        this.genero = genero;
        this.anio = anio;  
        this.duracion = duracion; 
    } 

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) { 
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenero() {
        return genero;
    } 

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }
 
    @Override
    public String toString() {

        return "Peliculas{Id=%d, Titulo=%d, Director='%s', Genero='%s', Anio=%d, Duracion='%s'}"
                .formatted(id, titulo, director, genero, anio, duracion);
    }
    
}    

