/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package prueba;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean repeticion = true;
        Scanner scanner = new Scanner(System.in);
        while (repeticion){
                // Mostrar opciones al usuario
                System.out.println("Selecciona una opción:");
                System.out.println("1 - Opciones del hospital");
                // Capturar entrada del usuario
                int opcion = scanner.nextInt();
                int i = 0;
                while (i != 10){
                    System.out.println("");
                    i = 1 + i;
                }

                // Ejecutar la clase correspondiente según la opción
                switch (opcion) {
                    case 1 -> {
                        System.out.println("----- Hospital -----");
                        System.out.println("1 - Agregar hospital");
                        System.out.println("2 - Ver hospitales");
                        int opcion2 = scanner.nextInt();
                        i = 0;
                        while (i != 10){
                            System.out.println("");
                            i = 1 + i;
                        }
                        
                        switch (opcion2){
                    case 1 -> Hospital.Ingresar_hospital();
                    case 2 -> Hospital.Ver_hospital();}
                    }
                  default -> System.out.println("Opción no válida.");
                }
            
        }
   }
}
        
        
