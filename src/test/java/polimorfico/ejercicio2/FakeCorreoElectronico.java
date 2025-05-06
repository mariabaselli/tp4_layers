package polimorfico.ejercicio2;

import ar.unrn.tp4.ejercicio2.model.EnvioMensaje;

public class FakeCorreoElectronico implements EnvioMensaje {
    String datos;

    @Override
    public void enviarEmail(String destinatario, String asunto, String mensaje) {
        this.datos = destinatario + asunto + mensaje;
    }

    public String obtenerDatos() {
        return this.datos;
    }
}
