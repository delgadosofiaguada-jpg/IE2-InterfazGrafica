package modelo;

import java.util.ArrayList;

public class InscripcionMateria implements Evaluable
{
    private Materia materia;
    private int totalClases;
    private int clasesAsistidas;
    private ArrayList<Double>notas;
    private static final int MAX_NOTAS=5;
    private static final double MINIMO_REGULAR = 75.0;
    
    public InscripcionMateria(Materia materia)
    {
        this.materia = materia;
        this.totalClases=0;
        this.clasesAsistidas=0;
        this.notas = new ArrayList<>();
    }
    
    public Materia getMateria() 
    {
        return materia;
    }
    public void registrarAsistencia(boolean presente)
    {
        totalClases++;
        if(presente) 
            clasesAsistidas++;
    }
    
    public void agregarNota(double nota) {
        if (nota < 0 || nota > 10)
            throw new IllegalArgumentException("La nota debe ser entre 0 y 10");
        if (notas.size() >= MAX_NOTAS)
            throw new IllegalStateException("No se pueden agregar mas de 5 notas");
        notas.add(nota);
    }

    public double getPorcentajeAsistencia() {
        if (totalClases == 0) return 0;
        return (clasesAsistidas * 100.0) / totalClases;
    }

    @Override
    public String getCondicion() {
        return getPorcentajeAsistencia() >= MINIMO_REGULAR ? "Regular" : "Libre";
    }
    
    @Override
    public double getNota() {
        if (notas.isEmpty()) return 0;
        double suma = 0;
        for (double n : notas) suma += n;
        return suma / notas.size();
    }

    @Override
    public boolean estaAprobado() {
        return getNota() >= 6 && getCondicion().equals("Regular");
    }

    public ArrayList<Double> getNotas() { return notas; }
    public int getTotalClases() { return totalClases; }
    public int getClasesAsistidas() { return clasesAsistidas; }

    // Persistencia: nombre|codigo|cuatri|anio|totalClases|clasesAsistidas|n1,n2,...
    public String toTexto() {
        StringBuilder sb = new StringBuilder(materia.toTexto());
        sb.append("|").append(totalClases).append("|").append(clasesAsistidas).append("|");
        for (int i = 0; i < notas.size(); i++) {
            sb.append(notas.get(i));
            if (i < notas.size() - 1) sb.append(",");
        }
        return sb.toString();
    }

    public static InscripcionMateria fromTexto(String linea) {
        String[] p = linea.split("\\|");
        Materia m = Materia.fromTexto(p[0]+"|"+p[1]+"|"+p[2]+"|"+p[3]);
        InscripcionMateria insc = new InscripcionMateria(m);
        insc.totalClases = Integer.parseInt(p[4]);
        insc.clasesAsistidas = Integer.parseInt(p[5]);
        if (p.length > 6 && !p[6].isEmpty())
            for (String n : p[6].split(","))
                insc.notas.add(Double.parseDouble(n));
        return insc;
    }
}