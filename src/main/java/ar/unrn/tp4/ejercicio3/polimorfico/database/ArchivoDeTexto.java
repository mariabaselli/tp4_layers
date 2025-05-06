package ar.unrn.tp4.ejercicio3.polimorfico.database;

import ar.unrn.tp4.ejercicio3.polimorfico.model.Concurso;
import ar.unrn.tp4.ejercicio3.polimorfico.model.RegistroDeInscriptos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class ArchivoDeTexto implements RegistroDeInscriptos {
    public static final String PATH_CONCURSOS = "C:\\Users\\Maria\\Documents\\universidad\\2025\\Objetos 2\\Tp's\\concursos.txt";
    private String pathGuardarInscriptos;

    public ArchivoDeTexto(String path) {
        this.pathGuardarInscriptos = path;
    }

    private static boolean estaVigenteConcurso(LocalDate fechaInicio, LocalDate fechaFin) {
        return fechaInicio.isBefore(LocalDate.now()) || fechaInicio.isEqual(LocalDate.now()) &&
                fechaFin.isAfter(LocalDate.now()) || fechaFin.isEqual(LocalDate.now());
    }

    @Override
    public void guardar(String apellido, String nombre, String telefono, String email, String idPersona, String idConcurso) {
        final Path path = Paths.get(this.pathGuardarInscriptos);
        try {
            String linea = apellido + "," + nombre + "," + telefono + "," + email + "," + idPersona + "," + idConcurso;
            Files.write(path, Arrays.asList(linea), StandardCharsets.UTF_16BE,
                    Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Concurso> todosLosConcursos() {
        String ruta = PATH_CONCURSOS;
        ArrayList<Concurso> listaConcursos = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(ruta));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String idConcurso = datos[0];
                String nombreConcurso = datos[1];
                String fechaInicioString = datos[2];
                String fechaFinString = datos[3];
                LocalDate fechaInicio = LocalDate.parse(fechaInicioString);
                LocalDate fechaFin = LocalDate.parse(fechaFinString);
                if (estaVigenteConcurso(fechaInicio, fechaFin)) {
                    listaConcursos.add(new Concurso(Integer.parseInt(idConcurso), nombreConcurso, fechaInicio, fechaFin));
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return listaConcursos;
    }
}
