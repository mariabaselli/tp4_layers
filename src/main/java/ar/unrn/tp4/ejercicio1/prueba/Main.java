package ar.unrn.tp4.ejercicio1.prueba;

import ar.unrn.tp4.ejercicio1.prueba.database.JdbcParticipantes;
import ar.unrn.tp4.ejercicio1.prueba.model.Participantes;

import java.awt.*;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new AgregarParticipante(new Participantes(new JdbcParticipantes()));
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
    }
}