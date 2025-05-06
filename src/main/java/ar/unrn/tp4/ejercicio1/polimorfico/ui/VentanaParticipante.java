package ar.unrn.tp4.ejercicio1.polimorfico.ui;

import ar.unrn.tp4.ejercicio1.polimorfico.model.Inscripcion;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;

public class VentanaParticipante extends JFrame {

    private Inscripcion inscripcion;
    private JTextField nombre;
    private JTextField telefono;
    private JTextField region;


    public VentanaParticipante(Inscripcion inscripcion) {
        this.inscripcion = inscripcion;
    }

    public void setupUIComponents() {
        setTitle("Add Participant");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.nombre = new JTextField(10);
        this.telefono = new JTextField(10);
        this.region = new JTextField(10);
        this.nombre.setText("");
        this.telefono.setText("");
        this.region.setText("China");
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new FlowLayout());
        contentPane.add(new JLabel("Nombre: "));
        contentPane.add(nombre);
        contentPane.add(new JLabel("Telefono: "));
        contentPane.add(telefono);
        contentPane.add(new JLabel("Region: "));
        contentPane.add(region);
        JButton botonCargar = new JButton("Cargar");
        botonCargar.addActionListener(e -> {
            try {
                onBotonCargar();
            } catch (SQLException e1) {
                throw new RuntimeException(e1);
            }
        });

        JButton botonListar = new JButton("Listar");
        botonListar.addActionListener(e -> {

        });
        contentPane.add(botonCargar);
        contentPane.add(botonListar);
        setContentPane(contentPane);
        contentPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        pack();
        setVisible(true);
    }

    private void onBotonCargar() throws SQLException {
        try {
            inscripcion.agregarParticipante(nombre.getText(), telefono.getText(), region.getText());
            JOptionPane.showMessageDialog(this, nombre.getText() + " ha sido inscripta exitosamente");
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        dispose();
    }

}
