/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.InscripcionDAO;
import java.util.ArrayList;
import modelo.Estudiante;
import modelo.InscripcionMateria;
import modelo.Materia;

/**
 *
 * @author guada
 */
public class Controlador {
    private Estudiante estudiante;
    private InscripcionDAO dao;
   
            
   public Controlador(){
       
      dao = new InscripcionDAO();
      estudiante = new Estudiante ( 
       
       "Sofia",
        "1234",
        "Sistemas",
          2023
      ); 
      for (InscripcionMateria i : dao.cargar()){
          estudiante.getMaterias().add(i);
      }
    }
    public void inscribirMateria(
        String nombre,
        String codigo,
        int cuatrimestre,
        int anio)
    {
        Materia materia = new Materia(
            nombre,
            codigo,
            cuatrimestre,
            anio);

        estudiante.inscribirse(materia);

        dao.guardar(estudiante.getMaterias());
    }  
    public void darDeBaja(String codigo)
    {
        estudiante.darDeBaja(codigo);

        dao.guardar(estudiante.getMaterias());
    }
    
    public void registrarAsistencia (String codigo, boolean presente)
    {
        InscripcionMateria insc = estudiante.buscarMateria(codigo);
        
        if (insc !=null) {
            insc.registrarAsistencia (presente);
            dao.guardar(estudiante.getMaterias());
        }
    }
    
    public void registrarNota (String codigo, double nota)
    {
        InscripcionMateria insc = estudiante.buscarMateria(codigo);
        if (insc != null) {
            insc.agregarNota(nota);
            dao.guardar(estudiante.getMaterias());   
        }
    }
    public Estudiante getEstudiante()
    {
        return estudiante;
    }
    
    public InscripcionMateria buscarMateria (String codigo)
    {
        return estudiante.buscarMateria(codigo);
    }
    
    public String generarReporteSituacion()
    {
        String reporte = "SITUACION GENERAL\n\n";

        for (InscripcionMateria i : estudiante.getMaterias())
        {
            reporte += i.getMateria().getNombre()
                + " - " + i.getCondicion()
                + " - " + String.format("%.1f", i.getPorcentajeAsistencia()) + "%"
                + " - Promedio: " + String.format("%.2f", i.getNota())
                + "\n";
        }

        reporte += "\nPromedio general: "
            + String.format("%.2f", estudiante.getPromedioGeneral());

        return reporte;
    }
    public String generarReporteAprobadas() {
    ArrayList<InscripcionMateria> ap = estudiante.getMateriasAprobadas();

    if (ap.isEmpty()) {
        return "No hay materias aprobadas aún.";
    }

    StringBuilder sb = new StringBuilder("=== MATERIAS APROBADAS ===\n\n");

    double max = 0;
    double min = 10;
    double suma = 0;

    for (InscripcionMateria i : ap) {
        double n = i.getNota();

        sb.append("- ")
          .append(i.getMateria().getNombre())
          .append(" | Nota: ")
          .append(String.format("%.2f", n))
          .append("\n");

        if (n > max) max = n;
        if (n < min) min = n;
        suma += n;
    }

        sb.append("\n--------------------------");
        sb.append("\nMáxima: ").append(String.format("%.2f", max));
        sb.append(" | Mínima: ").append(String.format("%.2f", min));
        sb.append(" | Promedio: ").append(String.format("%.2f", suma / ap.size()));

        return sb.toString();
    }
    public String generarReporteEnRiesgo() {

        ArrayList<InscripcionMateria> r =
            estudiante.getMateriasCriticasOrdenadas();

        if (r.isEmpty())
            return "No hay materias en riesgo actualmente.";

        StringBuilder sb =
            new StringBuilder("=== MATERIAS EN RIESGO (75%-85%) ===\n\n");

        sb.append(String.format("%-22s %s\n",
            "Materia",
            "Asistencia"));

        sb.append("------------------------------\n");

        for (InscripcionMateria i : r) {
            sb.append(String.format("%-22s %.1f%%\n",
                i.getMateria().getNombre(),
                i.getPorcentajeAsistencia()));
        }

        return sb.toString();
    } 
    public ArrayList<InscripcionMateria> buscarMateriaBonus(String termino) {

        ArrayList<InscripcionMateria> resultado =
            new ArrayList<>();

        for (InscripcionMateria i : estudiante.getMaterias()) {

            boolean matchNombre =
                i.getMateria()
                 .getNombre()
                 .toLowerCase()
                 .contains(termino.toLowerCase());

            boolean matchCodigo =
                i.getMateria()
                 .getCodigo()
                 .toLowerCase()
                 .contains(termino.toLowerCase());
            
        if (matchNombre || matchCodigo)
            resultado.add(i);
    }

        return resultado;
    }

}
   

