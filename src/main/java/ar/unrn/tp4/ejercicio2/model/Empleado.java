package ar.unrn.tp4.ejercicio2.model;

import java.time.LocalDate;
import java.time.MonthDay;

public record Empleado(String apellido, String nombre, String fechaNacimiento, String email) {

    public boolean esCumpleanos() {
        LocalDate fechaNacimiento = LocalDate.parse(this.fechaNacimiento);
        MonthDay cumple = MonthDay.of(fechaNacimiento.getMonth(), fechaNacimiento.getDayOfMonth());
        MonthDay hoy = MonthDay.from(LocalDate.now());
        return cumple.equals(hoy);
    }

    public String obtenerEmail() {
        return email;
    }
}
