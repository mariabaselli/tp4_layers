package ar.unrn.tp4.ejercicio2.main;

import ar.unrn.tp4.ejercicio2.database.ArchivoDeEmpleados;
import ar.unrn.tp4.ejercicio2.database.CorreoElectronico;
import ar.unrn.tp4.ejercicio2.model.Empleados;

public class Main {

    public static final String PATH_EMPLEADOS = "C:\\Users\\Maria\\Documents\\universidad\\2025\\Objetos 2\\Tp's\\datos_empleados.txt";
    public static final String FROM_EMAIL = "envio@prueba.com";

    public static void main(String[] args) {
        Empleados empleados = new Empleados(new ArchivoDeEmpleados(PATH_EMPLEADOS), new CorreoElectronico(FROM_EMAIL));
        empleados.enviarEmailCumpleanos();
    }

}
