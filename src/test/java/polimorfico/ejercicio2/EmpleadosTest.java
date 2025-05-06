package polimorfico.ejercicio2;

import ar.unrn.tp4.ejercicio2.model.Empleado;
import ar.unrn.tp4.ejercicio2.model.Empleados;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

//TODO: No recibo resultados con run coverage, dice "click edit to fix configuration settings"

public class EmpleadosTest {

    @Test
    public void envioEmail() {
        FakeCorreoElectronico fakeCorreo = new FakeCorreoElectronico();
        Empleados empleados = new Empleados(new FakeRegistroEmpleados(), fakeCorreo);
        empleados.enviarEmailCumpleanos();
        assertEquals("empleado@empleado.com" +
                "Feliz CumpleañosDesde" +
                " la empresa queremos desearle un feliz cumpleaños Juan Perez", fakeCorreo.obtenerDatos());
    }

    @Test
    public void esCumpleanos() {
        Empleado empleado = new Empleado("Perez", "Juana", "2005-02-06", "juana@prueba.com");
        assertFalse(empleado.esCumpleanos());
    }
}
