package ar.unrn.tp4.ejercicio1.polimorfico.database;

import ar.unrn.tp4.ejercicio1.polimorfico.model.Participante;
import ar.unrn.tp4.ejercicio1.polimorfico.model.RegistroDeParticipantes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcParticipante implements RegistroDeParticipantes {
    private Connection dbConn;


    private void setupBaseDeDatos() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/participantes";
        String user = "root";
        String password = "";
        this.dbConn = DriverManager.getConnection(url, user, password);
    }

    public void agregarParticipante(String nombre, String telefono, String region) throws SQLException {
        setupBaseDeDatos();
        PreparedStatement st = dbConn
                .prepareStatement("insert into participantes(nombre, telefono, region) values(?,?,?)");
        try {
            st.setString(1, nombre);
            st.setString(2, telefono);
            st.setString(3, region);
            st.executeUpdate();
        } finally {
            st.close();
        }

    }

    public List<Participante> listarParticipantes() throws SQLException {
        setupBaseDeDatos();
        List<Participante> participantes = new ArrayList<>();
        PreparedStatement st = dbConn
                .prepareStatement("SELECT * FROM participantes)");
        ResultSet rs = st.executeQuery();
        try {
            while (rs.next()) {

                Participante nuevoParticipante = new Participante(rs.getString("nombre"), rs.getString("telefono"),
                        rs.getString("region"));
                participantes.add(nuevoParticipante);
            }
        } finally {
            st.close();
        }
        return participantes;
    }
}
