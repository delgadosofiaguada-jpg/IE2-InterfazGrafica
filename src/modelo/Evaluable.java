package modelo;

public interface Evaluable 
{
    String getCondicion();
    double getNota();
    boolean estaAprobado();
    default void mostrarEstadoAcademico()
    {
        System.out.println("Condicion: " + getCondicion());
        System.out.println("Promedio: " + getNota());
    }
}