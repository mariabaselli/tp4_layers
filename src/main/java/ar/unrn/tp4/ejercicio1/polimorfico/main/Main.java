package ar.unrn.tp4.ejercicio1.polimorfico.main;

import ar.unrn.tp4.ejercicio1.polimorfico.database.JdbcParticipante;
import ar.unrn.tp4.ejercicio1.polimorfico.model.Inscripcion;
import ar.unrn.tp4.ejercicio1.polimorfico.ui.VentanaParticipante;

import java.awt.*;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                var ventana = new VentanaParticipante(new Inscripcion(new JdbcParticipante()));
                ventana.setupUIComponents();

            }
        });
    }
}