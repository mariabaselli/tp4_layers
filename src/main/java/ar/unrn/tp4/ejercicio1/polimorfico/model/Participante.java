package ar.unrn.tp4.ejercicio1.polimorfico.model;

public class Participante {
    public static final String CHINA = "China";
    public static final String USA = "US";
    public static final String EUROPA = "Europa";
    public static final String DESCRIPCION_REGION_DESCONOCIDA = "Region desconocida. Las conocidas son: China, US, Europa";
    public static final String DESCRIPCION_TELEFONO_INCORRECTO = "El teléfono debe ingresarse de la siguiente forma: NNNN-NNNNNN";
    public static final String FORMATO_TELEFONO = "\\d{4}-\\d{6}";
    public static final String DESCRIPCION_TELEFONO_AUSENTE = "Debe cargar un telefono";
    public static final String DESCRIPCION_NOMBRE_AUSENTE = "Debe cargar un nombre";
    private String nombre;
    private String telefono;
    private String region;


    public Participante(String nombre, String telefono, String region) {

        assertNombre(nombre);
        assertNumeroTelefono(telefono);
        assertValidezTelefono(telefono);
        assertRegion(region);


        this.nombre = nombre;
        this.telefono = telefono;
        this.region = region;

    }


    private void assertRegion(String region) {
        if (!region.equals(CHINA) && !region.equals(USA) && !
                region.equals(EUROPA)) {
            throw new RuntimeException(DESCRIPCION_REGION_DESCONOCIDA);
        }
    }

    private void assertValidezTelefono(String telefono) {
        String regex = FORMATO_TELEFONO;
        if (!(telefono.matches(regex))) {
            throw new RuntimeException(DESCRIPCION_TELEFONO_INCORRECTO);
        }
    }

    private void assertNumeroTelefono(String telefono) {
        if (telefono.equals("")) {
            throw new RuntimeException(DESCRIPCION_TELEFONO_AUSENTE);
        }
    }

    private void assertNombre(String nombre) {
        if (nombre.equals("")) {
            throw new RuntimeException(DESCRIPCION_NOMBRE_AUSENTE);
        }
    }


}


