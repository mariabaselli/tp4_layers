package ar.unrn.tp4.ejercicio1.polimorfico.model;

import java.sql.SQLException;
import java.util.List;

public interface RegistroDeParticipantes {

    void agregarParticipante(String nombre, String telefono, String region) throws SQLException;

    List<Participante> listarParticipantes() throws SQLException;
}
