    /*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package com.mycompany.alumno;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
*
* @author Isaias Quintero
*/

public class AlumnoRepository {

private Path rutaArchivo;

public AlumnoRepository() {
    rutaArchivo = Path.of("alumnos.txt");
    try {
        if (!Files.exists(rutaArchivo)) {
            Files.createFile(rutaArchivo);
        }
    } catch (IOException e) {
        System.out.println("Error al crear el archivo: " + e.getMessage());
    }
}

//crea
public void registrarAlumno(Alumno alumno) throws IOException {

    if (buscarAlumnoPorId(alumno.getId()) != null) {
        throw new IllegalArgumentException("El ID ya esta registrado.");
    }

    String linea = alumno.getId() 
            + " - " 
            + alumno.getNombre() 
            + System.lineSeparator();

    Files.writeString(rutaArchivo
            , linea
            , StandardOpenOption.APPEND);
}

// lee todos
public List<Alumno> obtenerTodos() throws IOException {

    List<String> lineas = Files.readAllLines(rutaArchivo);
    List<Alumno> alumnos = new ArrayList<>();

    for (int i = 0; i < lineas.size(); i++) {
        String linea = lineas.get(i);
        if (!linea.isEmpty()) {
            String[] partes = linea.split(" - ");
            int id = Integer.parseInt(partes[0]);
            String nombre = partes[1];

            Alumno al = new Alumno(id, nombre);
            alumnos.add(al);
        }
    }
    return alumnos;
}

//lee
public Alumno buscarAlumnoPorId(int idBuscado) throws IOException {
    List<Alumno> lista = obtenerTodos();

    for (int i = 0; i < lista.size(); i++) {
        if (lista.get(i).getId() == idBuscado) {
            return lista.get(i);
        }
    }
    return null;
}


//actualiza
public boolean actualizarAlumno(int id, String nuevoNombre) throws IOException {
    List<String> lineas = Files.readAllLines(rutaArchivo);

    for (int i = 0; i < lineas.size(); i++) {
        if (lineas.get(i).startsWith(id + " - ")) {
            lineas.set(i
                       , id 
                       + " - " 
                       + nuevoNombre);

            Files.write(rutaArchivo
                        , lineas);
            return true; 
        }
    }
    return false;
}

// borrar
public boolean eliminarAlumno(int id) throws IOException {
    List<String> lineas = Files.readAllLines(rutaArchivo);

    for (int i = 0; i < lineas.size(); i++) {
        if (lineas.get(i).startsWith(id + " - ")) {
            lineas.remove(i);
            Files.write(rutaArchivo, lineas);
            return true;
        }
    }
    return false;
}
}
