/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import modelo.InscripcionMateria;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author guada
 */
public class InscripcionDAO 
{
    private static final String ARCHIVO = "inscripciones.text";
    
    public ArrayList <InscripcionMateria> cargar ()
    {
        ArrayList<InscripcionMateria> lista = new ArrayList<>();
        File f = new File(ARCHIVO);
        return lista;
    }
}
