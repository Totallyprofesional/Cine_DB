
package Cine_db; 

import java.util.Scanner; 
 
public class Datos { 
    private Scanner sc = new Scanner(System.in);
    private String titulo, director, genero;
    private int a�o, duracion;
 
    public void agregar() {
        titulo = inputString("Ingrese t�tulo: ");
        director = inputString("Ingrese director: ");
        a�o = inputInt("Ingrese a�o: ");
        duracion = inputInt("Ingrese duraci�n: ");
        genero = inputString("Ingrese g�nero: ");
    }   

    private String inputString(String mensaje) {
        String input;
        do { 
            System.out.print(mensaje);
            input = sc.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("El dato est� vac�o.");
            }
        } while (input.isEmpty());
        return input;
    }
 
    private int inputInt(String mensaje) {
        int numero = 0;
        boolean valido;
        do {
            valido = true;
            System.out.print(mensaje);
            String input = sc.nextLine().trim();
            try {
                numero = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un valor-");
                valido = false;
            }
        } while (!valido);
        return numero;
    }


}    

