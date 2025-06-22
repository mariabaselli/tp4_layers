package ar.unrn.tp4.ejercicio2.model;

import java.util.ArrayList;

public class Empleados {
    //TODO es correcto tener el asunto y el mensaje como una constante?

    public static final String ASUNTO_EMAIL = "Feliz Cumpleaños";
    public static final String MENSAJE_EMAIL = "Desde la empresa queremos desearle un feliz cumpleaños ";
    ArrayList<Empleado> listaEmpleados;
    RegistroEmpleados registroEmpleados;
    EnvioMensaje servicioEnvio;

    public Empleados(RegistroEmpleados unRegistro, EnvioMensaje servicio) {
        listaEmpleados = new ArrayList<>();
        this.registroEmpleados = unRegistro;
        this.servicioEnvio = servicio;
    }

    public void enviarEmailCumpleanos() {
        obtenerEmpleados();
        for (Empleado empleado : listaEmpleados) {
            if (empleado.esCumpleanos()) {
                servicioEnvio.enviarEmail(empleado.obtenerEmail(), ASUNTO_EMAIL,
                        MENSAJE_EMAIL + empleado.nombre() + " " + empleado.apellido());
            }
        }
    }

    private void obtenerEmpleados() {
        this.listaEmpleados = registroEmpleados.obtenerEmpleados();
    }
}
