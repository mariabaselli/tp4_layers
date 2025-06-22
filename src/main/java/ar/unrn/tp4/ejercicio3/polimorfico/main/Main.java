package ar.unrn.tp4.ejercicio3.polimorfico.main;

import ar.unrn.tp4.ejercicio3.polimorfico.database.ArchivoDeTexto;
import ar.unrn.tp4.ejercicio3.polimorfico.model.Concursos;
import ar.unrn.tp4.ejercicio3.polimorfico.ui.VentanaInscriptos;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {


            @Override
            public void run() {
                try {
                    new Main().start();
                } catch (Exception e) {
// log exception...
                    System.out.println(e);
                }
            }
        });
    }

    private void start() {
        new VentanaInscriptos(new Concursos(new ArchivoDeTexto("C:\\Users\\Maria\\Documents\\universidad\\2025\\Objetos 2\\" +
                "Tp's\\inscriptos.txt")));
        //new VentanaInscriptos(new Concursos(new JdbcRegistroDeInscriptos()));

    }
}


