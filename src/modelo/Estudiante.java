package modelo;

import java.util.ArrayList;
import java.util.Comparator;
public class Estudiante extends PersonaAcademica implements Consultable
{
    private String carrera;
    private int anioIngreso;
    private ArrayList<InscripcionMateria>materias;
    
    public Estudiante(String nombre, String legajo, String carrera, int anioIngreso)
    {
        super(nombre, legajo);
        setCarrera(carrera);
        setAnioIngreso(anioIngreso);
        this.materias = new ArrayList<>();
    }        
    public int getAnioIngreso() {
    return anioIngreso;
}

public void setAnioIngreso(int anioIngreso) {
    if (anioIngreso < 2000)
        throw new IllegalArgumentException("Anio de ingreso invalido");
    this.anioIngreso = anioIngreso;
}
    public String getCarrera()
    {
        return carrera;
    }
    
    public void setCarrera(String c)
    {
        if(c == null || c.isEmpty())
            throw new IllegalArgumentException("Anio invalido");
        this.carrera=c;
    }
    
    public ArrayList<InscripcionMateria> getMaterias() { return materias; }

    public InscripcionMateria buscarMateria(String codigo) {
        for (InscripcionMateria i : materias)
            if (i.getMateria().getCodigo().equalsIgnoreCase(codigo)) return i;
        return null;
    }

    public void inscribirse(Materia m) {
        if (buscarMateria(m.getCodigo()) != null)
            throw new IllegalArgumentException("Ya esta inscripto en esa materia");
        materias.add(new InscripcionMateria(m));
    }

    public void darDeBaja(String codigo) {
        materias.removeIf(i -> i.getMateria().getCodigo().equalsIgnoreCase(codigo));
    }

    public InscripcionMateria getInscripcion(String codigo) {
        for (InscripcionMateria i : materias)
            if (i.getMateria().getCodigo().equalsIgnoreCase(codigo)) return i;
        return null;
    }

    public double getPromedioGeneral() {
        if (materias.isEmpty()) return 0;
        return calcularSumaNotasRecursiva(0) / materias.size();
    }

    private double calcularSumaNotasRecursiva(int index) {
        if (index == materias.size()) return 0;
        return materias.get(index).getNota() + calcularSumaNotasRecursiva(index + 1);
    }

    public ArrayList<InscripcionMateria> getMateriasCriticas() {
        ArrayList<InscripcionMateria> c = new ArrayList<>();
        for (InscripcionMateria i : materias) {
            double p = i.getPorcentajeAsistencia();
            if (p >= 75 && p <= 85) c.add(i);
        }
        return c;
    }
    
    @Override
    public void mostrarResumen() {
        System.out.println("Nombre: " + getNombre());
        System.out.println("Carrera: " + getCarrera());
        System.out.println("Anio ingreso: " + getAnioIngreso());
        System.out.println("Promedio: " + String.format("%.2f", getPromedioGeneral()));
    }
    
    //BONUS:BUSCAR MATERIA POR NOMBRE
    public ArrayList<InscripcionMateria> buscarporNombre(String nombre) 
    {
        ArrayList<InscripcionMateria> resultado = new ArrayList<>();
        for (InscripcionMateria i : materias) {
            if (i.getMateria().getNombre().toLowerCase().contains(nombre.toLowerCase()))
                resultado.add(i);
        }
        return resultado;
    }
    
    //BONUS: LISTA MATERIAS APROBADAS(nota >= 6 Y condicion Regular)
    public ArrayList<InscripcionMateria> getMateriasAprobadas() 
    {
        ArrayList<InscripcionMateria> aprobadas = new ArrayList<>();
        for (InscripcionMateria i : materias) {
            if (i.estaAprobado()) aprobadas.add(i);
        }
        return aprobadas;
    }
    
    //BONUS: MATERIAS EN RIESGO (75-85%) ordenadas ascendente por asistencia
    public ArrayList<InscripcionMateria> getMateriasCriticasOrdenadas() 
    {
        ArrayList<InscripcionMateria> criticas = new ArrayList<>();
        criticas.sort(Comparator.comparingDouble(InscripcionMateria::getPorcentajeAsistencia));
        return criticas;
    }

}