/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.InscripcionDAO;
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
        "1,2,3,4",
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
}
   

