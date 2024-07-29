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
                System.out.println("2 - Opciones del departamento");
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
                        System.out.println("3 - Eliminar hospital");
                        int opcion2 = scanner.nextInt();
                        i = 0;
                        while (i != 10){
                            System.out.println("");
                            i = 1 + i;
                        }
                        
                        switch (opcion2){
                    case 1 -> Hospital.Ingresar_hospital();
                    case 2 -> Hospital.Ver_hospital();
                    case 3 -> Hospital.Eliminar_hospital();
                      }
                    }
                    case 2 -> {
                        System.out.println("----- Departamento -----");
                        System.out.println("1 - Agregar departamento");
                        System.out.println("2 - Ver departamentos");
                        System.out.println("3 - Eliminar hospital");
                        int opcion2 = scanner.nextInt();
                        i = 0;
                        while (i != 10){
                            System.out.println("");
                            i = 1 + i;
                        }
                        
                        switch (opcion2){
                    case 1 -> Departamento.Ingresar_departamento();
                    case 2 -> Departamento.Ver_departamento();
                    case 3 -> Departamento.Eliminar_departamento();
                      }
                    }
                  default -> System.out.println("Opción no válida.");
                }
            
        }
   }
}
        
        
