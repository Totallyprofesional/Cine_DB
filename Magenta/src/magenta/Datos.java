
package magenta; 

import java.util.Scanner;
 
public class Datos {
    Scanner sc = new Scanner(System.in);
    String titulo;
    String director;
    int año;
    boolean valido;
    int duracion;
    String genero;  
    
    public void agregar (){  
        do { 
            System.out.print("Ingrese título: ");
            titulo = sc.nextLine(); 
            if (titulo.isEmpty()) {
                System.out.println("El nombre esta vacío.");
            } 
        } while (titulo.isEmpty());
        System.out.println("Título: " + titulo);
        
        do {
            System.out.print("Ingrese director: ");
            director = sc.nextLine(); 
            if (director.isEmpty()) {
                System.out.println("El nombre esta vacío.");
            }
        } while (director.isEmpty());
        System.out.println("Director: " + director);              
        
        do { 
            valido = true;
            System.out.print("Ingrese año: ");
            String year = sc.nextLine();
            try {
                año = Integer.parseInt(year);
                } catch (NumberFormatException e) {
                    System.out.println("Debe ingresar un valor.");
                    valido = false;
                } 
        } while (!valido);
        System.out.println("Año: " + año);
    
        do {  
            valido = true;
            System.out.print("Ingrese duración: ");
            String lenght = sc.nextLine();
            try {
                duracion = Integer.parseInt(lenght);
                } catch (NumberFormatException e) {
                    System.out.println("Debe ingresar un valor.");
                    valido = false;
                } 
        } while (!valido); 
        System.out.println("Duracion: " + duracion);

        do { 
            System.out.print("Ingrese genero: ");
            genero = sc.nextLine(); 
            if (genero.isEmpty()) {
                System.out.println("El nombre esta vacío.");
            } 
        } while (genero.isEmpty());
        System.out.println("Genero: " + genero);
    } 
    
}    

