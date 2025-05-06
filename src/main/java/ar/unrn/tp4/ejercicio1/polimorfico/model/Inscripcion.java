package ar.unrn.tp4.ejercicio1.polimorfico.model;

import java.sql.SQLException;
import java.util.List;

public class Inscripcion {

    RegistroDeParticipantes registro;

    public Inscripcion(RegistroDeParticipantes registro) {
        this.registro = registro;
    }

    //TODO Las líneas 14 y 15 me hacen ruido
    public void agregarParticipante(String nombre, String telefono, String region) throws SQLException {
        Participante participante = new Participante(nombre, telefono, region);
        this.registro.agregarParticipante(nombre, telefono, region);

    }

    public List<Participante> obtenerParticipantes() {
        try {
            return this.registro.listarParticipantes();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
