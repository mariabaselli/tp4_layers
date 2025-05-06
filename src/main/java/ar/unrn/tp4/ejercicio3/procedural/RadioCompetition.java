package ar.unrn.tp4.ejercicio3.procedural;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import java.util.List;

public class RadioCompetition {
    private JPanel contentPane;
    private JLabel lblName;
    private JTextField txtName;
    private JLabel lblLastName;
    private JTextField txtLastName;
    private JLabel lblId;
    private JTextField txtId;
    private JLabel lblPhone;
    private JTextField txtPhone;
    private JLabel lblEmail;
    private JTextField txtEmail;
    private JComboBox<String> comboBox;
    private JButton btnOk;
    private JLabel lblCompetition;
    private String idConcurso;

    public RadioCompetition() {
        var frame = new JFrame("Inscription to Competition");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 451, 229);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.setContentPane(contentPane);
        formElements();
        layout();
        frame.setVisible(true);
    }

    private static boolean estaVigenteConcurso(LocalDate fechaInicio, LocalDate fechaFin) {
        return fechaInicio.isBefore(LocalDate.now()) || fechaInicio.isEqual(LocalDate.now()) &&
                fechaFin.isAfter(LocalDate.now()) || fechaFin.isEqual(LocalDate.now());
    }

    private void formElements() {
        lblName = new JLabel("Nombre:");
        txtName = new JTextField();
        txtName.setColumns(10);
        lblLastName = new JLabel("Apellido:");
        txtLastName = new JTextField();
        txtLastName.setColumns(10);
        lblId = new JLabel("Dni:");
        txtId = new JTextField();
        txtId.setColumns(10);
        lblPhone = new JLabel("Telefono:");
        txtPhone = new JTextField();
        txtPhone.setColumns(10);
        lblEmail = new JLabel("Email:");
        txtEmail = new JTextField();
        txtEmail.setColumns(10);
        btnOk = new JButton("Ok");
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnOk.setEnabled(false);
                saveInscription();
                btnOk.setEnabled(true);
            }
        });
        lblCompetition = new JLabel("Concurso:");
        comboBox = new JComboBox<>();
        List<String> concursos = todosLosConcursos();
        for (String s : concursos) {
            comboBox.addItem(s);
        }
    }

    private List<String> todosLosConcursos() {
        String ruta = "C:\\Users\\Maria\\Documents\\universidad\\2025\\Objetos 2\\Tp's\\concursos.txt";
        List<String> listaConcursos = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(ruta));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                idConcurso = datos[0];
                String nombreConcurso = datos[1];
                String fechaInicioString = datos[2];
                String fechaFinString = datos[3];
                LocalDate fechaInicio = LocalDate.parse(fechaInicioString);
                LocalDate fechaFin = LocalDate.parse(fechaFinString);
                if (estaVigenteConcurso(fechaInicio, fechaFin)) {
                    listaConcursos.add(nombreConcurso);
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return listaConcursos;
    }

    private void saveInscription() {
        if (validations()) {
// Guarda en inscriptos.txt los datos de la persona y el concurso elegido
            String filePath = "C:\\Users\\Maria\\Documents\\universidad\\2025\\Objetos 2\\Tp's\\inscriptos.txt";
            String linea = txtLastName.getText() + "," + txtName.getText() + "," + txtPhone.getText() + "," + txtEmail.getText() + "," + txtId.getText() + "," + idConcurso;
            final Path path = Paths.get(filePath);
            try {
                Files.write(path, Arrays.asList(linea), StandardCharsets.UTF_16BE,
                        Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
                JOptionPane.showMessageDialog(null,
                        "Su inscripcion ha sido exitosa",
                        "Información",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private boolean validations() {
        if ("".equals(txtName.getText())) {
            JOptionPane.showMessageDialog(this.contentPane, "Nombre no puede ser vacio");
            return false;
        }
        if ("".equals(txtLastName.getText())) {
            JOptionPane.showMessageDialog(this.contentPane,
                    "apellido no puede ser vacio");
            return false;
        }
        if ("".equals(txtId.getText())) {
            JOptionPane.showMessageDialog(this.contentPane, "dni no puede ser vacio");
            return false;
        }
        if (!checkEmail(txtEmail.getText())) {
            JOptionPane.showMessageDialog(this.contentPane,
                    "email debe ser válido");
            return false;
        }
        if (!checkPhone(txtPhone.getText())) {
            JOptionPane.showMessageDialog(this.contentPane,
                    "El teléfono debe ingresarse de la siguiente forma: NNNN-NNNNNN");
            return false;
        }
        if (this.comboBox.getSelectedIndex() <= 0) {
            JOptionPane.showMessageDialog(this.contentPane, "Debe elegir un Concurso");
            return false;
        }
        return true;
    }

    private boolean checkEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    private boolean checkPhone(String telefono) {
        String regex = "\\d{4}-\\d{6}";
        return telefono.matches(regex);
    }

    private void layout() {
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(gl_contentPane
                .createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
                        .addGroup(gl_contentPane
                                .createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
                                        .createSequentialGroup()
                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                .addComponent(lblLastName).addComponent(lblId)
                                                .addComponent(lblPhone).addComponent(lblEmail)
                                                .addComponent(lblName).addComponent(lblCompetition))
                                        .addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                                        .addGroup(
                                                gl_contentPane.createParallelGroup(Alignment.LEADING, false)
                                                        .addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE,
                                                                Short.MAX_VALUE)
                                                        .addComponent(txtEmail, Alignment.TRAILING)
                                                        .addComponent(txtPhone, Alignment.TRAILING)
                                                        .addComponent(txtId, Alignment.TRAILING)
                                                        .addComponent(txtLastName, Alignment.TRAILING)
                                                        .addComponent(txtName, Alignment.TRAILING,
                                                                GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)))
                                .addComponent(btnOk, Alignment.TRAILING,
                                        GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap()));
        gl_contentPane
                .setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(txtName, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblName))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblLastName).addComponent(txtLastName,
                                                GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(lblId).addComponent(
                                                txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(
                                                gl_contentPane.createSequentialGroup().addComponent(lblPhone)
                                                        .addPreferredGap(ComponentPlacement.UNRELATED)
                                                        .addComponent(lblEmail))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(txtPhone, GroupLayout.PREFERRED_SIZE,
                                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE,
                                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED).addGroup(
                                                        gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                                                .addComponent(comboBox, GroupLayout.PREFERRED_SIZE,
                                                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(lblCompetition))))
                                .addPreferredGap(ComponentPlacement.RELATED).addComponent(btnOk)
                                .addContainerGap(67, Short.MAX_VALUE)));
        contentPane.setLayout(gl_contentPane);
    }
}

