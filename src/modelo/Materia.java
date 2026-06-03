package modelo;

public class Materia implements Consultable {
    private String nombre;
    private String codigo;
    private int cuatrimestre;
    private int anio;

    public Materia(String nombre, String codigo, int cuatrimestre, int anio) {
        this.nombre = nombre;
        setCodigo(codigo);
        setCuatrimestre(cuatrimestre);
        this.anio = anio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getCuatrimestre() {
        return cuatrimestre;
    }

    public int getAnio() {
        return anio;
    }

    public void setCodigo(String codigo) {
        if (codigo == null || codigo.length() < 3 || codigo.length() > 10) {
            throw new IllegalArgumentException("Codigo debe tener entre 3 y 10 caracteres");
        }
        this.codigo = codigo;
    }

    public void setCuatrimestre(int cuatrimestre) {
        if (cuatrimestre != 1 && cuatrimestre != 2) {
            throw new IllegalArgumentException("Cuatrimestre debe ser 1 o 2");
        }
        this.cuatrimestre = cuatrimestre;
    }

    public void setAnio(int anio) {
        if (anio < 2000) {
            throw new IllegalArgumentException("Año invalido");
        }
        this.anio = anio;
    }

    @Override
    public void mostrarResumen() {
        System.out.println("[" + codigo + "] " + nombre + " - " + cuatrimestre + "° cuatrimestre " + anio);
    }

    // ── Persistencia ──────────────────────────────────────────────────────────
    // Formato en el .txt:  nombre|codigo|cuatrimestre|anio
    public String toTexto() {
        return nombre + "|" + codigo + "|" + cuatrimestre + "|" + anio;
    }

    public static Materia fromTexto(String linea) {
        String[] partes = linea.split("\\|");
        String nombre       = partes[0];
        String codigo       = partes[1];
        int    cuatrimestre = Integer.parseInt(partes[2]);
        int    anio         = Integer.parseInt(partes[3]);
        return new Materia(nombre, codigo, cuatrimestre, anio);
    }
}
