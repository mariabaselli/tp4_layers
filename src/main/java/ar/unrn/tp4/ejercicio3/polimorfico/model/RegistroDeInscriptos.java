package ar.unrn.tp4.ejercicio3.polimorfico.model;

import java.util.ArrayList;

public interface RegistroDeInscriptos {
    void guardar(String apellido, String nombre, String telefono, String email, String idPersona, String idConcurso);

    ArrayList<Concurso> todosLosConcursos();
}
