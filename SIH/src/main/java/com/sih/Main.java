/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sih;

import com.sih.model.Persona;
import com.sih.dao.PersonaDAO;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PersonaDAO personaDAO = new PersonaDAO();
        
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Agregar Persona");
            System.out.println("2. Salir");
            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1 -> {
                    Persona persona = new Persona();
                    System.out.print("Nombre: ");
                    persona.setNombre(scanner.nextLine());
                    System.out.print("Dirección: ");
                    persona.setDireccion(scanner.nextLine());
                    System.out.print("Título: ");
                    persona.setTitulo(scanner.nextLine());
                    System.out.print("Nombre de Pila: ");
                    persona.setNombrePila(scanner.nextLine());
                    System.out.print("Segundo Nombre: ");
                    persona.setSegundoNombre(scanner.nextLine());
                    System.out.print("Apellidos: ");
                    persona.setApellidos(scanner.nextLine());
                    
                    personaDAO.addPersona(persona);
                    System.out.println("Persona agregada exitosamente.");
                }
                case 2 -> {
                    System.out.println("Saliendo...");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }
}
