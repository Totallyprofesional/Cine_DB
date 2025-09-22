
package magenta; 

import java.util.Scanner;
 
public class Datos {
    Scanner sc = new Scanner(System.in);
    String titulo;
    String director;
    int a�o;
    boolean valido;
    int duracion;
    String genero;  
    
    public void agregar (){  
        do { 
            System.out.print("Ingrese t�tulo: ");
            titulo = sc.nextLine(); 
            if (titulo.isEmpty()) {
                System.out.println("El nombre esta vac�o.");
            } 
        } while (titulo.isEmpty());
        System.out.println("T�tulo: " + titulo);
        
        do {
            System.out.print("Ingrese director: ");
            director = sc.nextLine(); 
            if (director.isEmpty()) {
                System.out.println("El nombre esta vac�o.");
            }
        } while (director.isEmpty());
        System.out.println("Director: " + director);              
        
        do { 
            valido = true;
            System.out.print("Ingrese a�o: ");
            String year = sc.nextLine();
            try {
                a�o = Integer.parseInt(year);
                } catch (NumberFormatException e) {
                    System.out.println("Debe ingresar un valor.");
                    valido = false;
                } 
        } while (!valido);
        System.out.println("A�o: " + a�o);
    
        do {  
            valido = true;
            System.out.print("Ingrese duraci�n: ");
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
                System.out.println("El nombre esta vac�o.");
            } 
        } while (genero.isEmpty());
        System.out.println("Genero: " + genero);
    } 
    
}    

