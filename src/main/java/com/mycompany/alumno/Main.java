/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.alumno;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Isaias Quintero
 */
public class Main {
    
    public static void main(String[] args) {
        
        try (Scanner teclado = new Scanner(System.in)) {
            AlumnoRepository repositorio = new AlumnoRepository();
            int opcion = 0;
            
            while (opcion != 6) {
                System.out.println("\nMENU GESTION DE ALUMNOS");
                System.out.println("1. Registrar nuevo alumno");
                System.out.println("2. Ver todos los alumnos");
                System.out.println("3. Buscar alumno por ID");
                System.out.println("4. Actualizar nombre de alumno");
                System.out.println("5. Eliminar alumno");
                System.out.println("6. Salir");
                System.out.print("Seleccione una opcion: ");
                
                try {
                    opcion = Integer.parseInt(teclado.nextLine());
                    
                    switch (opcion) {
                        case 1 ->                         {
                            System.out.println("\nRegistrar Alumno");
                            System.out.print("Ingrese ID: ");
                            int id = Integer.parseInt(teclado.nextLine());
                            System.out.print("Ingrese Nombre: ");
                            String nombre = teclado.nextLine();
                            Alumno nuevoAlumno = new Alumno(id, nombre);
                            try {
                                repositorio.registrarAlumno(nuevoAlumno);
                                System.out.println("Alumno registrado con exito");
                            } catch (IllegalArgumentException e) {
                                System.out.println("Error: " + e.getMessage());
                            }                              }
                        case 2 -> {
                            List<Alumno> lista = repositorio.obtenerTodos();
                            if (lista.isEmpty()) {
                                System.out.println("No hay alumnos registrados. El archivo esta vacio");
                            } else {
                                System.out.println("\nLista de Alumnos");
                                for (int i = 0; i < lista.size(); i++) {
                                    System.out.println(lista.get(i).toFileFormat());
                                }
                            }
                        }
                        case 3 ->                         {
                            System.out.println("\nBuscar Alumno");
                            System.out.print("Ingrese el ID a buscar: ");
                            int id = Integer.parseInt(teclado.nextLine());
                            Alumno encontrado = repositorio.buscarAlumnoPorId(id);
                            if (encontrado != null) {
                                System.out.println("Alumno encontrado: " + encontrado.toFileFormat());
                            } else {
                                System.out.println("Error: No se encontro ningun alumno con el ID " + id);
                            }                              }
                        case 4 ->                         {
                            System.out.println("\nActualizar Alumno");
                            System.out.print("Ingrese ID del alumno a modificar: ");
                            int id = Integer.parseInt(teclado.nextLine());
                            System.out.print("Ingrese el nuevo nombre: ");
                            String nuevoNombre = teclado.nextLine();
                            boolean seActualizo = repositorio.actualizarAlumno(id, nuevoNombre);
                            if (seActualizo) {
                                System.out.println("Alumno actualizado con exito");
                            } else {
                                System.out.println("Error: No existe un alumno con el ID proporcionado.");
                            }                              }
                        case 5 ->                         {
                            System.out.println("\nEliminar Alumno");
                            System.out.print("Ingrese ID del alumno a eliminar: ");
                            int id = Integer.parseInt(teclado.nextLine());
                            boolean seElimino = repositorio.eliminarAlumno(id);
                            if (seElimino) {
                                System.out.println("Alumno eliminado con exito");
                            } else {
                                System.out.println("Error: No se encontro el alumno con ese ID");
                            }                              }
                        case 6 -> System.out.println("Cerrando el programa. Hasta pronto");
                        default -> System.out.println("Opcion no valida. Intente nuevamente");
                    }
                    
                } catch (NumberFormatException e) {
                    System.out.println("Entrada invalida. Por favor ingrese un numero entero");
                } catch (IOException e) {
                    System.out.println("Error de Lectura/Escritura en el archivo: " + e.getMessage());
                }
            }
        }
        
    }
}
