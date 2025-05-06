package ar.unrn.tp4.ejercicio2.database;

import ar.unrn.tp4.ejercicio2.model.Empleado;
import ar.unrn.tp4.ejercicio2.model.RegistroEmpleados;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ArchivoDeEmpleados implements RegistroEmpleados {
    private String path;

    public ArchivoDeEmpleados(String path) {
        this.path = path;
    }

    @Override
    public ArrayList<Empleado> obtenerEmpleados() {
        ArrayList<Empleado> registroEmpleados = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String apellido = datos[0];
                String nombre = datos[1];
                String fechaNacimiento = datos[2];
                String email = datos[3];
                registroEmpleados.add(new Empleado(apellido, nombre, fechaNacimiento, email));
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return registroEmpleados;
    }
}
