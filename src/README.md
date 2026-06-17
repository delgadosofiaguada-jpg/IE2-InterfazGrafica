# Sistema de Autogestión Estudiantil — IE2 Interfaz Gráfica

Aplicación de escritorio en Java Swing que permite a un estudiante gestionar sus materias, asistencias, calificaciones y ver reportes académicos. Desarrollada como segunda instancia evaluativa, transformando el sistema de consola de IE1 en una aplicación con interfaz gráfica, arquitectura MVC + DAO y persistencia en archivos de texto.

---

## Integrantes y roles

| Integrante | Rol | Rama |
|---|---|---|
| Sofía Delgado | Coordinadora · Paquete modelo (Estudiante, Materia, InscripcionMateria, interfaces) | `feature/sofia-modelo` |
| Guadalupe Astudillo | Paquete dao + controlador (InscripcionDAO, Controlador) | `feature/guadalupe-dao-controlador` |
| Martina Roldán | Vista — diseño de los 9 JInternalFrames en NetBeans | `feature/martina-vista` |
| Leandro Gaido | Navegación, listeners, Main, integración final | `feature/leandro-formularios-main` |

---

## Instrucciones de ejecución

**Requisitos:**
- NetBeans 21 o superior
- JDK 17 o superior

**Pasos:**
1. Clonar el repositorio: `git clone https://github.com/delgadosofiaguada-jpg/IE2-InterfazGrafica`
2. Abrir el proyecto en NetBeans (`File → Open Project`)
3. Verificar que la clase principal sea `Main` (`clic derecho en el proyecto → Properties → Run → Main Class: Main`)
4. Ejecutar con `F6` o el botón Run

> El archivo `inscripciones.txt` se genera automáticamente en la carpeta raíz del proyecto al inscribir materias. Si no existe al iniciar, la aplicación arranca con lista vacía sin errores.

---

## Arquitectura del proyecto

```
src/
├── Main.java                           → Punto de entrada
├── controlador/
│   └── Controlador.java                → Orquesta modelo y DAO, sin imports Swing
├── dao/
│   └── InscripcionDAO.java             → Lee y escribe inscripciones.txt
├── modelo/
│   ├── Consultable.java
│   ├── Evaluable.java
│   ├── PersonaAcademica.java
│   ├── Estudiante.java
│   ├── Materia.java
│   └── InscripcionMateria.java
└── vista/
    ├── VistaPrincipalFrame.java        → JFrame principal con JDesktopPane
    ├── FrameBienvenida.java
    ├── FramePerfil.java
    ├── FrameGestionMaterias.java       → JTable + JList + búsqueda + CardLayout
    ├── FrameInscribirMateria.java
    ├── FrameAsistencias.java
    ├── FrameRegistrarCalificacion.java
    ├── frameSituacionGeneral.java
    ├── FrameMateriasEnRiesgo.java
    └── FrameMateriasAprobadas.java
```

**Restricciones respetadas:**
- El `Controlador` no tiene imports `javax.swing`
- El `DAO` no conoce ni la Vista ni el Controlador
- La `Vista` no tiene `FileWriter`, `FileReader` ni `new Materia()` — solo llama al Controlador

---

## Componentes Swing implementados

| Componente | Uso |
|---|---|
| `JLabel` | Títulos, etiquetas de campo, mensajes de estado |
| `JTable` | Tabla de materias con columnas nombre, código, cuatrimestre, año, condición, asistencia y promedio |
| `JList` | Lista de alertas (materias 75-85%) y lista de materias en riesgo |
| `JButton` | Inscribir, dar de baja, registrar asistencia, registrar nota, buscar, volver |
| `JMenuBar` | Menú de navegación en los 8 frames principales |
| `JOptionPane` | Confirmación antes de dar de baja + alerta cuando asistencia baja del 75% |
| `CardLayout` | Alterna entre vista "Todas las materias" y "Resultados de búsqueda" en GestionMaterias |
| `JScrollPane` | Envuelve la JTable y las JList |
| `BorderLayout` | Estructura principal en VistaPrincipalFrame |
| `FlowLayout` | Panel de bienvenida en FrameBienvenida |
| `JRadioButton` | Selección presente/ausente en FrameAsistencias |

---

## Funcionalidades

- Ver perfil del estudiante (nombre, legajo, carrera, año de ingreso, promedio general)
- Inscribir materia con validaciones (código único, cuatrimestre 1 o 2, código 3-10 caracteres)
- Dar de baja con confirmación previa
- Registrar asistencia con alerta automática si cae bajo el 75%
- Registrar nota (rango 0-10, máximo 5 notas por materia)
- Tabla de materias actualizada en tiempo real
- Lista de materias en zona de riesgo (75-85%)
- Reporte de situación general
- **Bonus:** búsqueda de materia por nombre o código con filtrado en tabla
- **Bonus:** reporte de materias en riesgo ordenadas por asistencia ascendente
- **Bonus:** reporte de materias aprobadas con nota máxima, mínima y promedio

---

## Persistencia

Los datos se guardan en `inscripciones.txt` en formato texto plano con separador `|`:

```
Programacion|PROG|2|2024|4|3|8.5,9.0
```

El archivo se carga al iniciar y se actualiza después de cada alta, baja o modificación. Si no existe, la aplicación arranca sin error.

---

## Diseño — Figma

[Ver prototipo en Figma](https://www.figma.com/design/hQp54Lh6oGs9BMlON5iTcU/Autogestion-Estudiantes?node-id=0-1&t=aCPow1rWFmQgLcFr-1)

---

## Desafíos encontrados

- **Migración de consola a Swing:** Adaptar la lógica de IE1 a la arquitectura MVC+DAO implicó reorganizar responsabilidades claras entre capas para no mezclar lógica de negocio con la interfaz.
- **Coordinación de ramas en Git:** Mantener las ramas sincronizadas y resolver conflictos al integrar el trabajo de los cuatro integrantes, especialmente al hacer merge a `feature/leandro-formularios-main`.
- **Componentes obligatorios de Swing:** Implementar CardLayout de forma funcional (con navegación real entre cartas) y no solo decorativa fue uno de los puntos más trabajados.
- **Persistencia en texto plano:** Diseñar el formato `toTexto()`/`fromTexto()` para que fuera robusto ante diferentes estados de las inscripciones (con o sin notas, con o sin asistencias).

---

## Uso de inteligencia artificial

Se utilizó IA (Claude de Anthropic) como herramienta de apoyo durante el desarrollo. Las consultas se concentraron principalmente en:

- Dudas sobre la arquitectura MVC+DAO y cómo separar correctamente las capas
- Depuración de errores en listeners de Swing y lógica de los frames
- Comprensión de componentes como CardLayout, GroupLayout y JInternalFrame
- Revisión y corrección de código antes de hacer merge a la rama principal

En todos los casos el código fue revisado y comprendido por cada integrante antes de incorporarlo al proyecto.
