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
    private static final String ARCHIVO = "inscripciones.txt";
    
    public ArrayList <InscripcionMateria> cargar ()
    {
        ArrayList<InscripcionMateria> lista = new ArrayList<>();
        File f = new File(ARCHIVO);
        if (!f.exists()) 
        {
           return lista; 
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {

        String linea;

        while ((linea = br.readLine()) != null) {

            if (!linea.trim().isEmpty()) {
                lista.add(InscripcionMateria.fromTexto(linea));
            }
        }
        
    } catch (IOException e) {
        System.out.println ("Error al leer :"+ e.getMessage());
    }
    return lista;
    }
}

    

