package polimorfico.ejercicio2;

import ar.unrn.tp4.ejercicio2.model.Empleado;
import ar.unrn.tp4.ejercicio2.model.RegistroEmpleados;

import java.util.ArrayList;

public class FakeRegistroEmpleados implements RegistroEmpleados {
    private ArrayList<Empleado> listaEmpleados;

    public FakeRegistroEmpleados() {
        this.listaEmpleados = new ArrayList<>();
    }

    @Override
    public ArrayList<Empleado> obtenerEmpleados() {
        Empleado empleado = new Empleado("Perez", "Juan", "2000-05-06", "empleado@empleado.com");
        listaEmpleados.add(empleado);
        return listaEmpleados;
    }

    Boolean startWith(String start) {
        return this.listaEmpleados.toString().startsWith(start);
    }
}
